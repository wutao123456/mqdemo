package com.wutao.netty.serial.marshalling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/5 22:02
 */
public class NettyServer {

    public static void main(String[] args) {
        //创建两个线程组bossGroup和workerGroup
        //bossGroup只是处理连接请求,workerGroup处理具体业务逻辑
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        //bossGroup和workerGroup含有的子线程个数默认为cpu核数*2

        try {


            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            System.out.println("客户socketChannel hashCode = "+socketChannel.hashCode());
                            pipeline.addLast(new ObjectDecoder(1024*1024//为防止异常码流和解码错位导致的内存溢出，将对象序列化后的最大字节数组长度设为1M
                                    , ClassResolvers.cacheDisabled(this.getClass().getClassLoader()//创建线程安全的WeakReferenceMap对类加载器进行缓存
                            )));
//                            pipeline.addLast(MarshallingCodeCFactory.marshallingEncoder());
//                            pipeline.addLast(MarshallingCodeCFactory.marshallingDecoder());
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });


            ChannelFuture future = bootstrap.bind(6668).sync();
            future.channel().closeFuture().sync();
        }catch (Exception o){
            o.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

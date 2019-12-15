package com.wutao.netty.proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/15 17:59
 * java通过netty实现代理服务器
 * https://www.jianshu.com/p/005acada04e3
 */
public class ProxyServer {

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
                    .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {

                        ChannelFuture connectFuture;

                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            Bootstrap bootstrap = new Bootstrap();
                            bootstrap.channel(ctx.channel().getClass())
                                    .handler(new SimpleChannelInboundHandler<ByteBuf>() {

                                        @Override
                                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                                            System.out.println("receive data");
                                        }
                                    });
                            bootstrap.group(ctx.channel().eventLoop());
                            connectFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1",80));
                        }

                        @Override
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                            if(connectFuture.isDone()){
                                //TODO
                            }
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

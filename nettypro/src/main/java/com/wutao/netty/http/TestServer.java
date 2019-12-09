package com.wutao.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/9 23:03
 */
public class TestServer {

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
            // 如果未设置或所设置的值小于1，Java将使用默认值50。
            b.option(ChannelOption.SO_BACKLOG, 1024);
            //是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）
            // 并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
            b.childOption(ChannelOption.SO_KEEPALIVE,true);
            ((ServerBootstrap)((ServerBootstrap)b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class))
                    .handler(new LoggingHandler(LogLevel.INFO)))
                    .childHandler(new TestServerInitializer());
            Channel ch = b.bind(8000).sync().channel();
            //sync()会同步等待连接操作结果，用户线程将在此wait()，直到连接操作完成之后，线程被notify(),用户代码继续执行
            //closeFuture()当Channel关闭时返回一个ChannelFuture,用于链路检测
            ch.closeFuture().sync();
        } finally {
            //资源优雅释放
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}

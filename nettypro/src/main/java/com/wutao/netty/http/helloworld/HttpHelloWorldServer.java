package com.wutao.netty.http.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.example.http.helloworld.HttpHelloWorldServerInitializer;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * @author wutao
 * @date 2019/12/3
 */
public final class HttpHelloWorldServer {
    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT;

    public HttpHelloWorldServer() {
    }

    public static void main(String[] args) throws Exception {
        SslContext sslCtx;
        if (SSL) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
            // 如果未设置或所设置的值小于1，Java将使用默认值50。
            b.option(ChannelOption.SO_BACKLOG, 1024);
            ((ServerBootstrap)((ServerBootstrap)b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)).handler(new LoggingHandler(LogLevel.INFO))).childHandler(new HttpHelloWorldServerInitializer(sslCtx));
            Channel ch = b.bind(PORT).sync().channel();
            System.err.println("Open your web browser and navigate to " + (SSL ? "https" : "http") + "://127.0.0.1:" + PORT + '/');
            ch.closeFuture().sync();
        } finally {
            //资源优雅释放
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    static {
        PORT = Integer.parseInt(System.getProperty("port", SSL ? "8443" : "8080"));
    }
}

package com.wutao.netty.telnet;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author wutao
 * @date 2019/12/3
 */
public class TelnetServer {

    static final int PORT;

    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new TelnetServerInitializer(null));
                serverBootstrap.bind(PORT).sync().channel().closeFuture().sync();
    }

    static {
        PORT = Integer.parseInt("8023");
    }
}

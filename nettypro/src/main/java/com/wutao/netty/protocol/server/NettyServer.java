package com.wutao.netty.protocol.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author wutao
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();


        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boos, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    //此处handler只创建一次,若需多个客户端连接则需开启注解@ChannelHandler.Sharable
                    ServerHandler serverHandler = new ServerHandler();
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new CustomDecode());
                        ch.pipeline().addLast(serverHandler);
                    }
                });
        bind(serverBootstrap,8000);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
    }

    /**
     * 递增绑定端口
     * @param serverBootstrap
     * @param port
     */
    public static void bind(final ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("绑定端口[ " + port + " ]成功");
                } else {
                    System.out.println("绑定端口[ " + port + " ]失败");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}


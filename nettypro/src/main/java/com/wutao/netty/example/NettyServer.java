package com.wutao.netty.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author wutao
 */
@ChannelHandler.Sharable
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();


        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boos, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                });
        bind(serverBootstrap,60401);
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
//                .bind(8000)
//                .addListener(new GenericFutureListener<Future<? super Void>>() {
//                    @Override
//                    public void operationComplete(Future<? super Void> future) throws Exception {
//                        if(future.isSuccess()){
//                            System.out.println("绑定端口成功");
//                        }else{
//                            System.out.println("绑定端口失败");
//                        }
//                    }
//                });
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


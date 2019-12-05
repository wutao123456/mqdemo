package com.wutao.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/5 22:12
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("server ctx = "+ctx);
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println("客户端发送消息: "+byteBuf.toString(CharsetUtil.UTF_8));
//        Channel channel = ctx.channel();
//        ChannelPipeline pipeline = ctx.pipeline();
//        System.out.println("客户端地址: "+channel.remoteAddress());


//        try {
//            Thread.sleep(10 * 1000);
//            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 瞄2", CharsetUtil.UTF_8));
//        }catch (Exception e){
//            System.out.println("发生异常: "+e.getMessage());
//        }

        //用户自定义任务
        //业务非常耗时->异步执行->提交到该channel对应的NioEventLoop的taskQueue
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 瞄2", CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生异常: "+e.getMessage());
                }
            }
        });

        //用户自定义定时任务
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 瞄3", CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生异常: "+e.getMessage());
                }
            }
        },5, TimeUnit.SECONDS);


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端 瞄1",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

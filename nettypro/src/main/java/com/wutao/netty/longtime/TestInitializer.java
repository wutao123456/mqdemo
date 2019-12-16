package com.wutao.netty.longtime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author wutao
 * @date 2019/12/16
 * netty处理耗时操作(此方式为Netty推荐)
 * https://www.cnblogs.com/stateis0/p/9062151.html
 */
public class TestInitializer extends ChannelInitializer<SocketChannel> {

    static EventExecutorGroup bussinessGroup = new DefaultEventExecutorGroup(10);
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(bussinessGroup,new BussinessHandler());
    }


    /**
     * 耗时操作
     */
    static class BussinessHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            Thread.sleep(10 * 1000);
        }
    }
}

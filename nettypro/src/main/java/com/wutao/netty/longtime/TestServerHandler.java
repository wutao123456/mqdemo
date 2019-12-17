package com.wutao.netty.longtime;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.Callable;

/**
 * @author wutao
 * @date 2019/12/16
 * netty处理耗时操作
 * https://www.cnblogs.com/stateis0/p/9062151.html
 */
public class TestServerHandler extends ChannelInboundHandlerAdapter {

    static final EventExecutorGroup group = new DefaultEventExecutorGroup(16);

    //我们模拟了一个耗时 10 秒的操作，于是，我们将这个任务提交到了一个自定义的业务线程池中，这样，就不会阻塞 Netty 的 IO 线程

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        final Object msgc = msg;
        final ChannelHandlerContext context = ctx;
        group.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
                ByteBuf byteBuf = (ByteBuf) msgc;
                byte[] bytes = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(bytes);
                String body = new String(bytes, "UTF-8");

                Thread.sleep(10 * 1000);
                System.err.println(body + "  " + Thread.currentThread().getName());

                String reqString = "hello server";
                ByteBuf rep = Unpooled.copiedBuffer(reqString, CharsetUtil.UTF_8);
                context.writeAndFlush(rep);
                return null;
            }
        });

    }
}

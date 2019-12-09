package com.wutao.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/9 23:06
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("myHttpServerCodec",new HttpServerCodec());
        pipeline.addLast("myTestServerHandler",new TestServerHandler());
    }
}

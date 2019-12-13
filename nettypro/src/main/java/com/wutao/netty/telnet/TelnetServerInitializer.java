package com.wutao.netty.telnet;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * @author wutao
 * @date 2019/12/3
 */
public class TelnetServerInitializer extends ChannelInitializer<SocketChannel> {
    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    private static final TelnetServerHandler SERVER_HANDLER = new TelnetServerHandler();
    private final SslContext sslCtx;

    public TelnetServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (this.sslCtx != null) {
            pipeline.addLast(new ChannelHandler[]{this.sslCtx.newHandler(ch.alloc())});
        }

        pipeline.addLast(new ChannelHandler[]{new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter())});
        pipeline.addLast(new ChannelHandler[]{DECODER});
        pipeline.addLast(new ChannelHandler[]{ENCODER});
        pipeline.addLast(new ChannelHandler[]{SERVER_HANDLER});
    }
}

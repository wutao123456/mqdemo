package com.wutao.netty.codec;

import io.netty.channel.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedStream;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/15 23:16
 * 写大型数据
 * https://www.cnblogs.com/shamo89/p/8600833.html
 */
public class ChunkWriteHandlerInitializer extends ChannelInitializer<Channel> {

    private final File file;

    private final SslContext sslContext;

    public ChunkWriteHandlerInitializer(File file, SslContext sslContext) {
        this.file = file;
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
//        pipeline.addLast(new SslHandler(sslContext.newEngine(channel.alloc())));// 将SslHandler添加到ChannelPipeline中
        pipeline.addLast(this.sslContext.newHandler(channel.alloc()));
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new WriteStreamHandler());

    }

    public final class WriteStreamHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            ctx.writeAndFlush(new ChunkedStream(new FileInputStream(file)));
        }
    }
}

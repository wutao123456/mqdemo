package com.wutao.netty.http.helloworld;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.ssl.SslContext;

/**
 * @author wutao
 * @date 2019/12/3
 */
public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;

    public HttpHelloWorldServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        if (this.sslCtx != null) {
            //开启SSL(加/解密) SslHandler
            p.addLast(new ChannelHandler[]{this.sslCtx.newHandler(ch.alloc())});
        }

        //添加HTTP服务端编/解码器
        p.addLast(new ChannelHandler[]{new HttpServerCodec()});
        p.addLast(new ChannelHandler[]{new HttpServerExpectContinueHandler()});
        p.addLast(new ChannelHandler[]{new HttpHelloWorldServerHandler()});
    }
}

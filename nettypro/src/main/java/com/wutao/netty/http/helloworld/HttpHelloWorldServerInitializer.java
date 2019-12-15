package com.wutao.netty.http.helloworld;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
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
        //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
        p.addLast(new HttpObjectAggregator(512*1024));
        //解压缩来自客户端的消息
        p.addLast(new HttpContentDecompressor());
        p.addLast(new ChannelHandler[]{new HttpServerExpectContinueHandler()});
        p.addLast(new ChannelHandler[]{new HttpHelloWorldServerHandler()});
    }
}

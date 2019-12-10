package com.wutao.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author wutao
 * @date 2019/12/10
 */
public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
        pipeline.addLast(new HttpServerCodec());
        //以块的方式来写的处理器
        pipeline.addLast(new ChunkedWriteHandler());
        //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
        pipeline.addLast(new HttpObjectAggregator(8192));
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
//        桢：
//        WebSocket规范中定义了6种类型的桢，netty为其提供了具体的对应的POJO实现。
//        WebSocketFrame：所有桢的父类，所谓桢就是WebSocket服务在建立的时候，在通道中处理的数据类型。
//        本列子中客户端和服务器之间处理的是文本信息。所以范型参数是TextWebSocketFrame。

        pipeline.addLast(new WebSocketFrameHandler());
    }
}

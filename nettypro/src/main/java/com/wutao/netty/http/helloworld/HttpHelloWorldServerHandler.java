package com.wutao.netty.http.helloworld;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * @author wutao
 * @date 2019/12/3
 */
public class HttpHelloWorldServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private static final byte[] CONTENT = new byte[]{72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100};

    public HttpHelloWorldServerHandler() {
    }

    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest)msg;
            boolean keepAlive = HttpUtil.isKeepAlive(req);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(CONTENT));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            if (keepAlive && req.protocolVersion().equals(HttpVersion.HTTP_1_0)) {
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            } else {
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            }

            ChannelFuture f = ctx.write(response);
            if (!keepAlive) {
                f.addListener(ChannelFutureListener.CLOSE);
            }
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

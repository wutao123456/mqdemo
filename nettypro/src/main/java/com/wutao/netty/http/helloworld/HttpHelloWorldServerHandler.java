package com.wutao.netty.http.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author wutao
 * @date 2019/12/3
 * SimpleChannelInboundHandler自动释放资源  ReferenceCountUtil.release(msg);
 */
public class HttpHelloWorldServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private static final byte[] CONTENT = new byte[]{72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100};

    public HttpHelloWorldServerHandler() {
    }

    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        //channel和channelPipeline的write方法会调用整个pipeline链上的handler
        //ChannelHandlerContext的write方法会从当前context找到下一个能够处理该事件handler
//        ctx.channel().write()
//        ctx.pipeline().write();
//        ctx.write();

        ctx.executor();
        //channelPipeline可以动态的添加、删除、替换channelHandler
//        ctx.pipeline().addLast();
//        ctx.pipeline().remove();
//        ctx.pipeline().replace();
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest)msg;
            boolean keepAlive = HttpUtil.isKeepAlive(req);
            ByteBuf byteBuf = Unpooled.copiedBuffer("这里是服务端", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(byteBuf));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
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

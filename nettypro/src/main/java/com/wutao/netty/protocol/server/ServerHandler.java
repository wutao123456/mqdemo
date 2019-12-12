package com.wutao.netty.protocol.server;

import com.wutao.netty.protocol.CustomProtocol;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wutao
 * @date 2019/12/12
 */
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<CustomProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol msg) throws Exception {
//        log.info("current channel hashCode = "+ctx.channel().hashCode());
        log.info(msg.toString());
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端收到: "+msg.toString(), CharsetUtil.UTF_8)).addListener(ChannelFutureListener.CLOSE);
    }
}

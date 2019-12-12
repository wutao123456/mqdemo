package com.wutao.netty.protocol.client;

import com.wutao.netty.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author wutao
 * @date 2019/12/12
 */
public class CustomEncode extends MessageToByteEncoder<CustomProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol msg, ByteBuf out) throws Exception {
        out.writeLong(msg.getHeader());
        out.writeBytes(msg.getContent().getBytes());
    }
}

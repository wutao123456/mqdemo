package com.wutao.netty.protocol.server;

import com.wutao.netty.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author wutao
 * @date 2019/12/12
 */
public class CustomDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        long header = in.readLong();
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        CustomProtocol protocol = new CustomProtocol(header,new String(bytes));
        out.add(protocol);
    }
}

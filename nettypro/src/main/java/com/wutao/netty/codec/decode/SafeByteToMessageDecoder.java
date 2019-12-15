package com.wutao.netty.codec.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/15 22:19
 */
public class SafeByteToMessageDecoder extends ByteToMessageDecoder{

    private static final int MAX_FRAME_SIZE = 1024;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readable = byteBuf.readableBytes();
        if(readable > MAX_FRAME_SIZE){
            byteBuf.skipBytes(readable);
            throw new TooLongFrameException("Frame too big");
        }
    }
}

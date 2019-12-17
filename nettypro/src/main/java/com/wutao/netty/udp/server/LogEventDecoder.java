package com.wutao.netty.udp.server;

import com.wutao.netty.udp.LogEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author wutao
 * @date 2019/12/17
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        ByteBuf data = msg.content();
//        System.out.println("test>>>>"+msg.content().toString(CharsetUtil.UTF_8));
        String str = msg.content().toString(CharsetUtil.UTF_8);
        int index = str.indexOf(LogEvent.SEPARATOR);
        String fileName = str.substring(0,index);
        String received = str.substring(index + 1,str.length());
        LogEvent logEvent = new LogEvent(fileName, received);
        out.add(logEvent);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

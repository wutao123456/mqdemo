package com.wutao.netty.udp.client;

import com.wutao.netty.udp.LogEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author wutao
 * @date 2019/12/17
 */
public class LogEventEncoder extends MessageToMessageEncoder<LogEvent> {

    private final InetSocketAddress remoteAddress;

    public LogEventEncoder(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, LogEvent msg, List<Object> out) throws Exception {
        byte[] file = msg.getLogfile().getBytes(CharsetUtil.UTF_8);
        byte[] data = msg.getMsg().getBytes(CharsetUtil.UTF_8);
        ByteBuf byteBuf = ctx.channel().alloc().buffer(file.length +data.length + 1);
        byteBuf.writeBytes(file);
        byteBuf.writeByte(LogEvent.SEPARATOR);
        byteBuf.writeBytes(data);
        out.add(new DatagramPacket(byteBuf,remoteAddress));
    }
}

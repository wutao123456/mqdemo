package com.wutao.netty.stickpacket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author wutao
 * @date 2019/12/13
 * 定长字符串处理粘包/拆包问题
 */
public class FixedLengthFrameDecoderClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        //在于server建立连接后，即发送请求报文
                        public void channelActive(ChannelHandlerContext ctx) {
                            System.out.println(">>>>>>>>>>建立连接");
                            ByteBuf A = Unpooled.buffer().writeBytes("A".getBytes());
                            ByteBuf BC = Unpooled.buffer().writeBytes("BC".getBytes());
                            ByteBuf DEFG = Unpooled.buffer().writeBytes("DEFG".getBytes());
                            ByteBuf HI = Unpooled.buffer().writeBytes("HI".getBytes());
                            ctx.writeAndFlush(A);
                            ctx.writeAndFlush(BC);
                            ctx.writeAndFlush(DEFG);
                            ctx.writeAndFlush(HI);
                        }
                    });
                }
            });
            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1",8080).sync(); // (5)
            // Wait until the connection is closed.
//            f.channel().closeFuture().sync();
            Channel channel = f.channel();
            ChannelFuture lastWriteFuture = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String line = reader.readLine();
                if(line == null){
                    break;
                }

                lastWriteFuture = channel.writeAndFlush(Unpooled.copiedBuffer(line.getBytes()));
                if("bye".equals(line)){
                    channel.closeFuture().sync();
                    break;
                }
            }

            if(lastWriteFuture != null){
                lastWriteFuture.sync();
            }

        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}

package com.wutao.netty.telnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author wutao
 * @date 2019/12/3
 */
public class TelnetClient {

    public static void main(String[] args) throws Exception{
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new TelnetClientInitalizer());
        Channel channel = bootstrap.connect("127.0.0.1",8023).sync().channel();
        ChannelFuture lastWriteFuture = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String line = reader.readLine();
            if(line == null){
                break;
            }

            lastWriteFuture = channel.writeAndFlush(line +"\r\n");
            if("bye".equals(line)){
                channel.closeFuture().sync();
                break;
            }
        }

        if(lastWriteFuture != null){
            lastWriteFuture.sync();
        }
    }
}

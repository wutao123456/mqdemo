package com.wutao.netty.udp.client;

import com.wutao.netty.udp.LogEvent;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

/**
 * @author wutao
 * @date 2019/12/17
 */
public class LogEventBroadcaster {

    private File file;

    private EventLoopGroup eventLoopGroup;

    private Bootstrap bootstrap;

    public LogEventBroadcaster(InetSocketAddress address,File file) {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST,true)
//                .option(ChannelOption.SO_RCVBUF, 2048 * 2048)
//                .option(ChannelOption.SO_SNDBUF, 2048 * 2048)
                .handler(new LogEventEncoder(address));
        this.file = file;
    }

    public void run()throws IOException {
        Channel channel = bootstrap.bind(0).syncUninterruptibly().channel();
        long pointer = 0;

        for(;;){
            long len = file.length();
            if(len < pointer){
                pointer = len;
            }else if(len > pointer){
                RandomAccessFile raf = new RandomAccessFile(file,"r");
                raf.seek(pointer);
                String line = null;
                while ((line = raf.readLine()) != null){
                    channel.writeAndFlush(new LogEvent(null,-1,file.getName(),line));
                }

                pointer = raf.getFilePointer();
                raf.close();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
                break;
            }
        }

    }

    public void stop(){
        eventLoopGroup.shutdownGracefully();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }

        LogEventBroadcaster broadcaster = new LogEventBroadcaster(new InetSocketAddress("255.255.255.255",Integer.parseInt(args[0])),new File(args[1]));
        try {
            broadcaster.run();
        } catch (IOException e) {
            broadcaster.stop();
        }
    }
}

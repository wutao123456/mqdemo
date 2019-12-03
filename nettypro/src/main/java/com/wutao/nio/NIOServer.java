package com.wutao.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/2 22:57
 */
public class NIOServer {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        Selector selector = Selector.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册后的selectionKey的数量= "+selector.keys().size());

        while (true){
            if(selector.select(1000) == 0){
                System.out.println("服务器等待了1秒,未连接");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("发生事件的selectionKey的数量= "+selector.selectedKeys().size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    SocketChannel channel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功,生成了一个socketChannel "+channel.hashCode());
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                    System.out.println("注册后的selectionKey的数量= "+selector.keys().size());
                }

                if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端: "+new String(byteBuffer.array()));
                }

                iterator.remove();
            }
        }
    }
}

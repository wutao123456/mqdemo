package com.wutao.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/3 21:35
 */
public class GroupChatServer {

    private static Selector selector;

    private static ServerSocketChannel serverSocketChannel;

    private static int port = 6667;

    public GroupChatServer() {
        try{
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void listen(){
        try{
            while (true){
                int count = selector.select(2000);
                if(count > 0){
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()){
                        SelectionKey key = keys.next();
                        if(key.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress()+" 上线");
                        }

                        if(key.isReadable()){
                            readData(key);
                        }

                        //删除当前key,防止重复处理
                        keys.remove();
                    }
                }else {
                    System.out.println("等待...");
                }
            }
        }catch (Exception e){

        }
    }

    public void readData(SelectionKey key){
        SocketChannel socketChannel = null;
        try{
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);
            if(count >0){
                String msg = new String(byteBuffer.array());
                System.out.println("from 客户端 "+msg);
                sendIntoToOtherClients(msg,socketChannel);
            }
        }catch (Exception e){
            try{
                System.out.println(socketChannel.getRemoteAddress() +"下线");
                //取消注册
                key.cancel();
                //关闭通道
                socketChannel.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }

    public void sendIntoToOtherClients(String msg,SocketChannel self)throws Exception{
        System.out.println("服务器转发消息中...");
        for(SelectionKey selectionKey:selector.keys()){
            Channel targetChannel = selectionKey.channel();
            if(targetChannel instanceof SocketChannel && targetChannel != self){
                ((SocketChannel) targetChannel).write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }

}

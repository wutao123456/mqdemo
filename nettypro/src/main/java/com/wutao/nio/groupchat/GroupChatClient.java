package com.wutao.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/3 21:58
 */
public class GroupChatClient {

    private String host = "127.0.0.1";

    private int port = 6667;

    private Selector selector;

    private SocketChannel socketChannel;

    private String userName;

    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(host,port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName + " is ok");
    }

    private void sendInfo(String info){
        info = userName +" say "+info;
        try{
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void readInfo()throws Exception{
        int readChannel = selector.select();
        if(readChannel > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isReadable()){
                    SocketChannel ch = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    ch.read(byteBuffer);
                    String str = new String(byteBuffer.array());
                    System.out.println(str.trim());
                }

                iterator.remove();
            }
        }else{
//            System.out.println("没有可用通道");
        }
    }

    public static void main(String[] args) throws Exception{
        GroupChatClient chatClient = new GroupChatClient();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try{
                        chatClient.readInfo();
                        Thread.sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            chatClient.sendInfo(line);
        }
    }
}

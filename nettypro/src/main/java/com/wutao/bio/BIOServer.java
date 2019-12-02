package com.wutao.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/1 17:31
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务启动了");

        while (true){
            System.out.println("线程信息 id="+Thread.currentThread().getId() + ",名称="+Thread.currentThread().getName());
            System.out.println("等待连接...");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket){
        try{
            System.out.println("线程信息 id="+Thread.currentThread().getId() + ",名称="+Thread.currentThread().getName());
            byte [] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                System.out.println("线程信息 id="+Thread.currentThread().getId() + ",名称="+Thread.currentThread().getName());
                //如果当前线程没有数据可读取,线程就阻塞在read操作,造成系统资源浪费
                System.out.println("read...");
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));
                }else{
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

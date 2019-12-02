package com.wutao.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/2 22:11
 */
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.socket().bind(inetSocketAddress);


        ByteBuffer[] byteBuffers = new ByteBuffer[2];

        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);


        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true){
            int read = 0;
            while (read <messageLength){
                long l = socketChannel.read(byteBuffers);
                read += l;
                System.out.println("byteRead="+read);
                Arrays.asList(byteBuffers).stream().map(buffer -> "position="+buffer.position() +",limit="+buffer.limit()).forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());
            long byteWrite = 0;
            while (byteWrite < messageLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            Arrays.asList(byteBuffers).forEach(buffer -> buffer.clear());

            System.out.println("byteRead = " + read +",byteWrite = "+byteWrite +",messageLength = "+messageLength);
        }

    }
}

package com.wutao.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/1 21:41
 */
public class NIOFileChannel01 {

    public static void main(String[] args) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        String str = "hello world";

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}

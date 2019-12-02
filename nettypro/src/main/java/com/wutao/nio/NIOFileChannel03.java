package com.wutao.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/1 22:02
 */
public class NIOFileChannel03 {

    public static void main(String[] args)throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("1.txt"));
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        while (true){
            byteBuffer.clear();
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read = "+read);
            if(read == -1){
                break;
            }

            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();


    }
}

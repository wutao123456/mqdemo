package com.wutao.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/1 22:10
 */
public class NIOFileChannel04 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(new File("1.txt"));
        FileChannel sourceChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("3.txt");
        FileChannel destChannel = fileOutputStream.getChannel();

        destChannel.transferFrom(sourceChannel,0,sourceChannel.size());
        sourceChannel.close();
        destChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}

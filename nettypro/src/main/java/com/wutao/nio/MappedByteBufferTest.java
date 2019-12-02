package com.wutao.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/2 21:56
 * MappedByteBuffer 可让文件直接在内存(堆外内存)中修改
 */
public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        /**
         * 参数2：可以修改的起始位置
         * 参数3：映射到内存中的大小
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);

        mappedByteBuffer.put(0,(byte)'H');
        mappedByteBuffer.put(3,(byte)'9');

        randomAccessFile.close();
        System.out.println("修改成功");
    }
}

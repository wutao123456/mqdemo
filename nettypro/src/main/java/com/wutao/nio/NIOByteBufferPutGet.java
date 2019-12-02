package com.wutao.nio;

import java.nio.ByteBuffer;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/1 22:23
 */
public class NIOByteBufferPutGet {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for (int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte) i);
        }

        byteBuffer.flip();

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

        readOnlyBuffer.put((byte) 100);

    }
}

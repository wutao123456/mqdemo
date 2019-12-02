package com.wutao.nio;

import java.nio.IntBuffer;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/1 18:13
 */
public class BasicBuffer {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for(int i=0;i<intBuffer.capacity();i++){
            intBuffer.put(i*2);
        }

        //buffer读写切换
        intBuffer.flip();

        //自定义设置position和limit
        intBuffer.position(1);
        intBuffer.limit(3);
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}

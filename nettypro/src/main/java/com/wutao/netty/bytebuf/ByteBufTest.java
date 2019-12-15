package com.wutao.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/12/14 20:13
 */
public class ByteBufTest {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", Charset.defaultCharset());
        System.out.println(byteBuf.readerIndex());
        System.out.println(byteBuf.writerIndex());
        System.out.println(byteBuf.capacity());
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        byteBuf.readByte();
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println(byteBuf.readerIndex());
//        System.out.println(byteBuf.discardReadBytes());
        System.out.println(byteBuf.readableBytes());
//        byteBuf.capacity(50);
        System.out.println(byteBuf.writableBytes());
        int index = byteBuf.forEachByte(ByteBufProcessor.FIND_ASCII_SPACE);
        System.out.println("space index is = "+index);


        //浅拷贝
        //生成一个新的ByteBuf实例,与原引用共享数据副本slice,duplicate,readSlice
        ByteBuf slice = byteBuf.slice(0,5);
        slice.setByte(0,(byte)'w');
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println(slice.toString(CharsetUtil.UTF_8));
        System.out.println(slice.getByte(0) == byteBuf.getByte(0));


        //深拷贝
        //新的ByteBuf实例拥有自己的数据副本
        ByteBuf copy = byteBuf.copy(0,5);
        copy.setByte(0,(byte)'t');
        System.out.println(byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println(copy.toString(CharsetUtil.UTF_8));
        System.out.println(copy.getByte(0) == byteBuf.getByte(0));

        //get set方法不会影响readIndex writeIndex
        System.out.println(byteBuf.getByte(0));
        System.out.println("原索引不变："+byteBuf.readerIndex());
        System.out.println("原索引不变："+byteBuf.writerIndex());
        //byteBuf剩余字节数组
        System.out.println(byteBuf.retain().toString(CharsetUtil.UTF_8));;
    }
}

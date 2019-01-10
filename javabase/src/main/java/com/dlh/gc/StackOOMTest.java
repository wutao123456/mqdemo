package com.dlh.gc;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/10 23:21
 */
public class StackOOMTest {

    /**
     * 测试栈内存溢出
     * @param args
     */
    public static void main(String[] args) {
        stackOutOfMemoryError(1);
    }

    public static void stackOutOfMemoryError(int depth) {
        depth++;
        stackOutOfMemoryError(depth);
    }

}

package com.dlh.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/10 23:19
 */
public class HeapOOMTest {

    /**
     * 测试堆内存溢出
     * 设置VM参数-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(i++);
        }
    }

}

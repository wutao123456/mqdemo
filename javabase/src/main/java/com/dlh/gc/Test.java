package com.dlh.gc;

import java.util.ArrayList;
import java.util.List;

public class Test {

    /**
     * 打印堆信息
     * -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCApplicationStoppedTime
     * 测试finalize方法
     * @param args
     */
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setId(1);
        p1 = null;
        Person p2 = new Person();
        p2.setId(2);
        p2 = null;
        System.gc();

        while (true){
            List<Object> list = new ArrayList<>();
            list.add(new Person());
        }

    }
}

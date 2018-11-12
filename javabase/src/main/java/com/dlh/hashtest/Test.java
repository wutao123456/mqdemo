package com.dlh.hashtest;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wutao
 * @date 2018/11/9
 */
public class Test extends Thread {

    static HashMap<Integer,Integer> map = new HashMap<>();
    static AtomicInteger at = new AtomicInteger();

    @Override
    public void run() {
        while (at.get() < 100000){
            map.put(at.get(),at.get());
            at.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        Test test1 = new Test();
        Test test2 = new Test();
        Test test3 = new Test();
        Test test4 = new Test();
        Test test5 = new Test();
        test1.start();
        test2.start();
        test3.start();
        test4.start();
        test5.start();

    }
}

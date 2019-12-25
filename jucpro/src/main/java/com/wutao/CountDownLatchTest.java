package com.wutao;

import java.util.concurrent.CountDownLatch;

/**
 * @author wutao
 * @date 2019/12/25
 */
public class CountDownLatchTest {

    private static final int N = 10;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(N);
        for (int i = 0; i < N; i++) {
            new Thread(new Worker(countDownLatch), "thread-" + i).start();
        }
        try {
            //等待所有线程执行完再执行main线程
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(Thread.currentThread().getName());
    }
}

class Worker implements Runnable {

    private CountDownLatch start;

    public Worker(CountDownLatch start) {
        this.start = start;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        start.countDown();
    }
}

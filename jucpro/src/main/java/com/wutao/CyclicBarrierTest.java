package com.wutao;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wutao
 * @date 2019/12/25
 * 栅栏：等待所有线程执行完
 */
public class CyclicBarrierTest {

    private static final int N = 5;

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("****** 所有运动员已准备完毕，发令枪：跑！******");
            }
        });

        for (int i = 0; i < N; i++) {
            Thread t = new Thread(new Task(cb), "运动员 " + i);
            t.start();
            if(i == 3){
                t.interrupt();
            }
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CyclicBarrier 被损坏："+cb.isBroken());
    }

    static class Task implements Runnable {

        private CyclicBarrier cb;

        public Task(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "准备完成");
                cb.await();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断");
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + "BrokenBarrierException");
            }
        }
    }
}

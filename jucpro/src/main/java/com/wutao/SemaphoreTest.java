package com.wutao;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {

        int N = 8;//工人数
        Semaphore semaphore = new Semaphore(5, true);//机器数,是否公平
        for (int i = 0; i < N; i++) {
            Thread t = new Thread(new Worker(i, semaphore), "thread-" + i);
            t.start();
        }

    }

    static class Worker extends Thread{

        private int num;

        private Semaphore semaphore;

        public Worker(int num,Semaphore semaphore){
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+" 启动了 ");
                this.semaphore.acquire();
                System.out.println("工人 " + this.num + " 占用了一台机器");
                Thread.sleep(2000);
                this.semaphore.release();
                System.out.println("工人 " + this.num + " 释放了机器");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

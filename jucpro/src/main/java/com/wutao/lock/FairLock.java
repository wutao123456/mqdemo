package com.wutao.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wutao
 * @date 2019/12/25
 * 公平锁
 */
public class FairLock {

    private ReentrantLock lock = new ReentrantLock(true);

    private int count = 0;

    public void print(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" 获得锁");
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws Exception{
        final FairLock fairLock = new FairLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                fairLock.print();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable, "t" + i);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
            Thread.sleep(1);
        }

    }
}


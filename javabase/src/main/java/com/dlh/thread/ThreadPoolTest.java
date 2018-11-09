package com.dlh.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" run");
            }
        });
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,4,8, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);

        System.out.println("核心线程数"+executor.getCorePoolSize());
        System.out.println("线程池数"+executor.getPoolSize());
        System.out.println("队列任务数"+executor.getQueue().size());
    }


}

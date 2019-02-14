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
        // 如果线程池中的线程数量大于corePoolSize时，
        // 如果某线程空闲时间超过keepAliveTime，线程将被终止，
        // 直至线程池中的线程数目不大于corePoolSize；
        // 如果允许为核心池中的线程设置存活时间，
        // 那么核心池中的线程空闲时间超过keepAliveTime，
        // 线程也会被终止。
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

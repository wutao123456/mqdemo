package com.wutao.executor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;
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
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,4,8, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(2));
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        executor.execute(t1);
        //如果工作线程数小于核心线程池上限（CorePoolSize），则直接新建一个工作线程并执行任务；
        //如果工作线程数大于等于CorePoolSize，则尝试将任务加入到队列等待以后执行。如果加入队列失败了（比如队列已满的情况），
        // 则在总线程池未满的情况下（CorePoolSize ≤ 工作线程数 ＜ maximumPoolSize）新建一个工作线程立即执行任务，否则执行拒绝策略。

        System.out.println("核心线程数"+executor.getCorePoolSize());
        System.out.println("线程池数"+executor.getPoolSize());
        System.out.println("队列任务数"+executor.getQueue().size());



        //ScheduledThreadPoolExecutor不会像ThreadPoolExecutor那样再去创建归属于非核心线程池的工作线程，而是直接返回。
        // 也就是说，在ScheduledThreadPoolExecutor中，一旦核心线程池满了，就不会再去创建工作线程。
        ThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);
    }


}

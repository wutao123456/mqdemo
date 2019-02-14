package com.dlh.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/14 22:18
 */
public class Threadpools {

    public static void main(String[] args) {
       ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
       System.out.println("****************************newCachedThreadPool*******************************");
       for(int i=0;i<4;i++){
           newCachedThreadPool.execute(new ThreadForpools(i));
       }

       ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
       System.out.println("****************************newFixedThreadPool*******************************");
       for(int i=0;i<4;i++) {
           final int index=i;
           newFixedThreadPool.execute(new ThreadForpools(index));
       }

       ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
       System.out.println("****************************newFixedThreadPool*******************************");
       for(int i=0;i<4;i++) {
           final int index=i;
           newScheduledThreadPool.schedule(new ThreadForpools(index),3, TimeUnit.SECONDS);
       }

       ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
       System.out.println("****************************newFixedThreadPool*******************************");
       for(int i=0;i<4;i++) {
            final int index=i;
            newSingleThreadExecutor.execute(new ThreadForpools(index));
        }
    }
}

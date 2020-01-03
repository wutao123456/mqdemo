package com.wutao.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class ExecutorTest {

    public static void main(String[] args) {
        //默认使用DefaultThreadFactory
        Executors.newFixedThreadPool(1,new MyThreadFactoy());
        //可换成线程池,默认超过60s未使用,该线程被回收
        Executors.newCachedThreadPool();
        //内部还是调用new ScheduledThreadPoolExecutor(corePoolSize)
        Executors.newScheduledThreadPool(3);



    }
}

/**
 * 名称、守护程序状态、ThreadGroup 等等，线程池中的线程非常多，
 * 如果每个线程都这样手动配置势必非常繁琐，而ThreadFactory 作为一个线程工厂可以让我们从这些繁琐的线程状态设置的工作中解放出来，
 * 还可以由外部指定ThreadFactory实例，以决定线程的具体创建方式。
 */
class MyThreadFactoy implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}

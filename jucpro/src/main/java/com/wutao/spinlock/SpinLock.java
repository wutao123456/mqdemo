package com.wutao.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wutao
 * @date 2019/12/24
 * 自旋锁：是指当一个线程在获取锁的时候，如果锁已经被其它线程获取，那么该线程将循环等待，然后不断的判断锁是否能够被成功获取，直到获取到锁才会退出循环。
 * https://www.jianshu.com/p/9d3660ad4358?utm_source=oschina-app
 */
public class SpinLock {

    private AtomicReference cas = new AtomicReference();

    public void lock(){
        Thread current = Thread.currentThread();
        while (!cas.compareAndSet(null,current)){

        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        cas.compareAndSet(current,null);
    }
}

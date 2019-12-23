package com.wutao.lock;

/**
 * @author wutao
 * @date 2019/12/23
 * Java多线程锁重入是指: 在已经获得锁的同步方法或同步代码块内部可以调用锁定对象的其他同步方法, 不需要重新获取锁.
 *
 * 注意: 要弄清楚锁定对象是谁:
 * 1.非静态同步方法锁定的是当前this对象.
 * 2.静态同步方法锁定的是class字节码.
 * 3.同步代码块则锁定的是括号中的对象.
 *
 * 不要使用String对象作为锁对象，防止常量池引发同步问题。
 *
 */
public class SyncReUseService {

    public synchronized void service1(){
        System.out.println("service1");
        this.service2();
    }

    public synchronized void service2(){
        System.out.println("service2");
        this.service3();
    }

    public synchronized void service3(){
        System.out.println("service3");
    }

    public static void main(String[] args) {
        SyncReUseServiceThread t = new SyncReUseServiceThread();
        t.start();
    }
}

class SyncReUseServiceThread extends Thread{
    @Override
    public void run() {
        super.run();
        SyncReUseService syncReUseService = new SyncReUseService();
        syncReUseService.service1();
    }
}
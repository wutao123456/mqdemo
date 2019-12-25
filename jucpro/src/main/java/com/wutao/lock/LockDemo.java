package com.wutao.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wutao
 * @date 2019/12/25
 */
public class LockDemo {

    private ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception{

        final LockDemo demo = new LockDemo();
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                demo.doSomeThing1();
            }
        };
        t1.start();

        Thread t2 = new Thread("t2"){
            @Override
            public void run() {
                demo.doSomeThing3();
            }
        };

        t2.start();
        Thread.sleep(100);
        t2.interrupt();
    }

    public void doSomeThing1(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获取到 锁");
//            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放 锁");
        }
    }

    public void doSomeThing2(){
        if(lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + "获取到 锁");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放 锁");
            }
        }
    }

    public void doSomeThing3(){
        try {
            //锁阻塞可被打断,sleep也可被打断
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+"获取到 锁");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName()+" interrupted");
        } finally {
            if(lock.isLocked()){
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放 锁");
            }
        }
    }

}

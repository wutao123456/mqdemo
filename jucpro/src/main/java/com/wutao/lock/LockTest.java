package com.wutao.lock;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wutao
 * @date 2019/12/23
 */
public class LockTest {

    private int queueSize= 10;

    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);

    /**
     * ReentrantLock类的其中一个构造器提供了指定公平策略 / 非公平策略的功能，默认为非公平策略。
     *
     * 公平策略：在多个线程争用锁的情况下，公平策略倾向于将访问权授予等待时间最长的线程。也就是说，相当于有一个线程等待队列，先进入等待队列的线程后续会先获得锁，这样按照“先来后到”的原则，对于每一个等待线程都是公平的。
     * 非公平策略：在多个线程争用锁的情况下，能够最终获得锁的线程是随机的（由底层OS调度）。
     */
    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    public synchronized static void main(String[] args) {
        LockTest lockTest = new LockTest();
        Consumer consumer = lockTest.new Consumer();
        Producer producer = lockTest.new Producer();
        producer.start();
        consumer.start();
        producer.interrupt();
        consumer.interrupt();

    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        volatile boolean flag = true;
        private void consume(){
            while (flag){
                lock.lock();
                try {
                    while (queue.size() == 0){
                        try {
                            System.out.println("队列空,等待数据");
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            flag = false;
                        }
                    }

                    queue.poll();//取出队首元素
                    notFull.signalAll();
                    System.out.println("从队列取走一个元素,队列剩余"+queue.size()+"个元素");
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        volatile boolean flag = true;
        private void produce(){
            while (flag){
                lock.lock();
                try {
                    while (queue.size() == queueSize){
                        try {
                            System.out.println("队列满,等待剩余空间");
                            notFull.await();
                        } catch (InterruptedException e) {
                            flag = false;
                        }
                    }

                    queue.offer(1);//插入元素
                    notEmpty.signalAll();
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                }finally {
                    lock.unlock();
                }
            }
        }
    }


}

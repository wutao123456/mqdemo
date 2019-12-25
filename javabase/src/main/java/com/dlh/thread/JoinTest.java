package com.dlh.thread;

public class JoinTest {

    static class Jin implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i <1000 ; i++) {
                System.out.println(Thread.currentThread().getName()+"  &&&&&&   "+i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Jin t1 = new Jin();
        Jin t2 = new Jin();
        Thread thread1 = new Thread(t1,"t1");
        Thread thread2 = new Thread(t2,"t2");
        thread1.start();

        thread2.start();
        thread1.join();
        //在main线程里调用thread1.join(),main会阻塞到thread1执行完毕再执行下面


        System.out.println("thread main");
    }
}

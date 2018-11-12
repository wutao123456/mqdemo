package com.dlh.thread;

public class JoinTest {

    static class Jin extends Thread{
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
        t1.start();
//        t1.join();
        t2.start();
    }
}

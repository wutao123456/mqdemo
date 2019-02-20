package com.dlh.thread;

/**
 * @author wutao
 * @date 2019/2/20
 */
public class WaitTest {


    public static void main(String[] args) {

        final Object obj = new Object();

        Thread t1 = new Thread(){
            @Override
            public void run(){
                try{
                    synchronized (obj){
                        for(int i=1;i<100;i+=2){
                            System.out.print(i + " ");
                            obj.wait();
                            obj.notify();
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized (obj){
                        for(int i=2;i<=100;i+=2){
                            System.out.print(i + " ");
                            obj.notify();
                            obj.wait();
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };

        t1.start();
        t2.start();
    }
}

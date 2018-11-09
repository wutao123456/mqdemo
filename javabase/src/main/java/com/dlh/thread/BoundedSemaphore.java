package com.dlh.thread;

public class BoundedSemaphore {

    private int signal = 0;
    private int bound = 0;

    public BoundedSemaphore(int bound) {
        this.bound = bound;
    }

    public synchronized void take()throws InterruptedException{
        while (this.signal == bound){
            System.out.println(Thread.currentThread().getName()+"坑位已满");
            wait();
        }
        System.out.println(Thread.currentThread().getName()+" 已占坑");
        this.signal++;
        notify();
    }

    public synchronized void release()throws InterruptedException{
        while (this.signal == 0){
            wait();
        }
        System.out.println(Thread.currentThread().getName()+"释放坑");
        this.signal--;
        notify();
    }

    public static void main(String[] args) {
        final BoundedSemaphore boundedSemaphore = new BoundedSemaphore(5);
        for(int i=0;i<6;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        boundedSemaphore.take();
                        boundedSemaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

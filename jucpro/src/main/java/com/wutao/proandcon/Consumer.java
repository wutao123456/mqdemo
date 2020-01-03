package com.wutao.proandcon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class Consumer implements Runnable{

    private Channel channel;

    public Consumer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            try {
                Object o = channel.take();
                System.out.println(Thread.currentThread().getName()+" consume: "+o.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.yield();
        }
    }

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1,true);
        Channel channel = new Channel(blockingQueue);
        Producer producer = new Producer(channel);
        Consumer c1 = new Consumer(channel);
        Consumer c2 = new Consumer(channel);

        new Thread(producer).start();
        new Thread(c1).start();
        new Thread(c2).start();

    }
}


package com.wutao.proandcon;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class Producer implements Runnable{

    private Channel channel;

    public Producer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            String v = String.valueOf(ThreadLocalRandom.current().nextInt());
            Data d = new Data(v);
            try {
                channel.put(d);
                System.out.println(Thread.currentThread().getName()+" produce: "+d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.yield();
        }
    }
}

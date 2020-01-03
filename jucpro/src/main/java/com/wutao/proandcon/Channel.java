package com.wutao.proandcon;

import java.util.concurrent.BlockingQueue;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class Channel {

    private final BlockingQueue blockingQueue;

    public Channel(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void put(Object o)throws InterruptedException{
        blockingQueue.put(o);
    }

    public Object take()throws InterruptedException{
        return blockingQueue.take();
    }
}

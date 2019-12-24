package com.wutao.spinlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wutao
 * @date 2019/12/24
 * 公平的自旋锁
 */
public class TicketLockV2 {

    private AtomicInteger ticketNum = new AtomicInteger();

    private AtomicInteger serviceNum = new AtomicInteger();

    private ThreadLocal<Integer> ticketHolder = new ThreadLocal<>();

    public void lock(){
        int currentTicketNum = ticketNum.incrementAndGet();
        ticketHolder.set(currentTicketNum);
        while (currentTicketNum != serviceNum.get()){

        }
    }

    public void unlock(){
        int currentTicketNum = ticketHolder.get();
        serviceNum.compareAndSet(currentTicketNum,currentTicketNum + 1);
    }
}

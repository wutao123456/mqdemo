package com.wutao.spinlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wutao
 * @date 2019/12/24
 * 线程获取锁,返回排队号,释放锁,传入排队号,
 * 这样是有风险的，因为这个排队号是可以被修改的，一旦排队号被不小心修改了，那么锁将不能被正确释放
 */
public class TicketLock {

    private AtomicInteger ticketNum = new AtomicInteger();

    private AtomicInteger serviceNum = new AtomicInteger();

    public int lock(){
        int currentTicketNum = ticketNum.incrementAndGet();
        while (currentTicketNum != serviceNum.get()){

        }

        return currentTicketNum;
    }

    /**
     * 释放锁：传入当前持有锁线程的排队号
     * @param ticketNum
     */
    public void unlock(int ticketNum){
        serviceNum.compareAndSet(ticketNum,ticketNum + 1);
    }

}

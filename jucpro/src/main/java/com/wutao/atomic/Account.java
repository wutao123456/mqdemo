package com.wutao.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author wutao
 * @date 2019/12/25
 */
public class Account {

    private volatile int money;

    private static final AtomicIntegerFieldUpdater<Account> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Account.class,"money");

    public Account(int money) {
        this.money = money;
    }

    public void increMonry(){
        fieldUpdater.incrementAndGet(this);
    }

    public int getMoney(){
        return money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                '}';
    }
}

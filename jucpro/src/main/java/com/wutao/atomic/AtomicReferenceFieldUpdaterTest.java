package com.wutao.atomic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @date 2019/12/25
 * 以一种线程安全的方式操作非线程安全对象的某些字段
 */
public class AtomicReferenceFieldUpdaterTest {

    public static void main(String[] args) throws Exception {
        Account account = new Account(0);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(new MyTask(account));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(account.toString());
    }

}

class MyTask implements Runnable {

    private Account account;

    public MyTask(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.increMonry();
    }
}
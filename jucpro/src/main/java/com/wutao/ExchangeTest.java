package com.wutao;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class ExchangeTest {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangeThread t1 = new ExchangeThread("t1",exchanger);
        ExchangeThread t2 = new ExchangeThread("t2",exchanger);
        ExchangeThread t3 = new ExchangeThread("t3",exchanger);
        t1.start();
        t2.start();
        t3.start();
    }
}

class ExchangeThread extends Thread{

    private String name;

    private Exchanger<String> exchanger;

    public ExchangeThread(String name,Exchanger<String> exchanger){
        super();
        this.name = name;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程["+name+"]拿到消息"+exchanger.exchange(name+"-msg",5, TimeUnit.SECONDS));
            System.out.println("线程["+name+"]over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
        }
    }
}

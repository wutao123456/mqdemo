package com.wutao.phaser;

import java.util.concurrent.Phaser;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class PhaserTest1 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        for (int i = 0; i < 10; i++) {
            //通过register方法注册Phaser的参与者数量为10
            phaser.register();
            new Thread(new Task(phaser), "Thread-" + i).start();
        }
    }
}

class Task implements Runnable {

    private Phaser phaser;

    public Task(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        //当某个线程调用arriveAndAwaitAdvance方法后，arrive数量会加1，
        //如果数量没有满足总数（参与者数量10），当前线程就是一直等待，当最后一个线程到达后，所有线程都会继续往下执行。
        int i = phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + ": 执行完任务，当前phase =" + i + "");
    }
}

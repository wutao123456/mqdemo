package com.wutao.phaser;

import java.util.concurrent.Phaser;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class PhaserTest3 {

    public static void main(String[] args) {
        final int repeats = 3;
        Phaser phaser = new Phaser(){

            //if true this phaser should terminate
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("---------------PHASE[" + phase + "],Parties[" + registeredParties + "] ---------------");
                return phase + 1 >=repeats || registeredParties == 0;
            }
        };

        for (int i = 0; i < 5; i++) {
            phaser.register();
            new Thread(new Task3(phaser),"Thread-"+i).start();
        }

    }
}

class Task3 implements Runnable {

    private final Phaser phaser;

    public Task3(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        //当某个线程调用arriveAndAwaitAdvance方法后，arrive数量会加1，
        //如果数量没有满足总数（参与者数量10），当前线程就是一直等待，当最后一个线程到达后，所有线程都会继续往下执行。
        while (!phaser.isTerminated()) {
            int i = phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + ": 执行完任务");
        }
    }
}

package com.wutao.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wutao
 * @date 2019/12/24
 */
public class AtomicIntegerTest {

    public static void main(String[] args) {
        AtomicInteger ai = new AtomicInteger();

        for(int i=0;i<10;i++){
            new Thread(new Accumlator(ai),"Thread-"+i).start();
        }
    }

    static class Accumlator implements Runnable {

        private AtomicInteger ai;

        public Accumlator(AtomicInteger ai) {
            this.ai = ai;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                System.out.println(ai.incrementAndGet());
            }
        }
    }
}



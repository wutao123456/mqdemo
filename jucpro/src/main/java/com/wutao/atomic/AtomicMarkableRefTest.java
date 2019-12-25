package com.wutao.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author wutao
 * @date 2019/12/25
 */
public class AtomicMarkableRefTest {

    private static AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference<>(1,false);

    public static void main(String[] args) {
        Thread main = new Thread("main"){
            @Override
            public void run() {
                boolean marked = atomicMarkableReference.isMarked();
                try {
                    TimeUnit.SECONDS.sleep(1);//等待1秒,一般让干扰线程执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean cas = atomicMarkableReference.compareAndSet(1,2,marked,!marked);
                System.out.println(cas);
            }
        };

        Thread other = new Thread("other"){
            @Override
            public void run() {
                Thread.yield();//确保t1先执行
                atomicMarkableReference.compareAndSet(1,2,atomicMarkableReference.isMarked(),!atomicMarkableReference.isMarked());
                atomicMarkableReference.compareAndSet(2,1,atomicMarkableReference.isMarked(),atomicMarkableReference.isMarked());
            }
        };

        main.start();
        other.start();

    }
}

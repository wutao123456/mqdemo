package com.wutao.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author wutao
 * @date 2019/12/25
 * 解决ABA问题(增加版本号)
 */
public class ABA {

    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,0);

    public static void main(String[] args) {
        Thread main = new Thread("main"){
            @Override
            public void run() {
                int stamp = atomicStampedReference.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(1);//等待1秒,一般让干扰线程执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean cas = atomicStampedReference.compareAndSet(1,2,stamp,stamp+1);
                System.out.println(cas);
            }
        };

        Thread other = new Thread("other"){
            @Override
            public void run() {
                Thread.yield();//确保t1先执行
                atomicStampedReference.compareAndSet(1,2,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName() +" ref: "+ atomicStampedReference.getReference() + " version: "+atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(2,1,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName() +" ref: "+ atomicStampedReference.getReference() + " version: "+atomicStampedReference.getStamp());
            }
        };

        main.start();
        other.start();

    }
}

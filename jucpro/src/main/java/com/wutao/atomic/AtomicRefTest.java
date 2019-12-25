package com.wutao.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wutao
 * @date 2019/12/25
 */
public class AtomicRefTest {

    public static void main(String[] args) throws Exception {
        AtomicReference<Integer> ref = new AtomicReference<>(1000);
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Task(ref), "thread-" + i);
            list.add(t);
            t.start();
        }

        for (Thread t : list) {
            t.join();
        }

        System.out.println(ref.get());
    }
}

class Task implements Runnable {

    private AtomicReference<Integer> ref;

    public Task(AtomicReference ref) {
        this.ref = ref;
    }

    @Override
    public void run() {
        for (; ; ) {
            int oldValue = ref.get();
            if (ref.compareAndSet(oldValue, oldValue + 1)) {
                break;
            }
        }
    }
}

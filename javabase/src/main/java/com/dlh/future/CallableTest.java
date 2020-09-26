package com.dlh.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2020/9/26 22:39
 */
public class CallableTest {

    public static void main(String[] args) throws Exception{
        List<Integer> data = new ArrayList<>();
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<Future<Integer>>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            Future<Integer> future = service.submit(read(i));
            queue.add(future);
        }

        int queueSize = queue.size();
        for (int i = 0; i < queueSize; i++) {
            Integer d = queue.take().get();
            data.add(d);
        }

        System.out.printf(data.toString());
        service.shutdown();
    }

    private static Callable<Integer> read(final int index){
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return index + 10;
            }
        };

        return callable;
    }
}

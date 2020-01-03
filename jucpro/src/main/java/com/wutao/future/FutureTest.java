package com.wutao.future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTest {

    static class MyCallable implements Callable<String>{

        private String test;

        public MyCallable(String param) {
            this.test = param;
        }

        @Override
        public String call() throws Exception {
            StringBuilder builder = new StringBuilder();
            for(int i=0;i<10;i++){
                builder.append(this.test);
            }
            System.out.println("任务执行完毕");
            return builder.toString();
        }
    }

    public static void main(String[] args) throws Exception{
        MyCallable myCallable = new MyCallable("a");
        FutureTask f = new FutureTask(myCallable);
//        1.直接执行
        f.run();

//        2.线程池执行
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.submit(f);

//        3.手动创建线程池执行
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,1,0, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
//        poolExecutor.execute(f);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("另一个任务");
            }
        }).start();

//        System.out.println("正在处理其他");
        Thread.sleep(2000);
        //f.get()调用这个方法会导致程序阻塞，必须等到子线程结束后才会得到返回值
//        f.cancel(true);
        System.out.println(f.get());
    }
}

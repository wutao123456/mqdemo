package com.wutao.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author wutao
 * @date 2020/1/6
 */
public class Main {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ArraySumTask task = new ArraySumTask(new int[1000], 1, 1000);
        //提交任务的三种方式
        //1.同步且有返回结果
        Long result = forkJoinPool.invoke(task);
        //2.异步且没有返回结果
        forkJoinPool.execute(task);
        //3.异步且有返回结果
        ForkJoinTask<Long> futureTask = forkJoinPool.submit(task);

        //来检查任务是否已经抛出异常或已经被取消了
        if (futureTask.isCompletedAbnormally()) {
            System.out.println(futureTask.getException());
        }

        try {
            System.out.println("result: " + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

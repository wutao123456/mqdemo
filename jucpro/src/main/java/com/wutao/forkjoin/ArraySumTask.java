package com.wutao.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author wutao
 * @date 2020/1/6
 */
public class ArraySumTask extends RecursiveTask<Long> {

    private final int[] array;

    private final int begin;

    private final int end;

    private static final int THRESHOLD = 100;

    public ArraySumTask(int[] array, int begin, int end) {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if (end - begin + 1 < THRESHOLD) {
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            int mid = (begin + end) / 2;
            ArraySumTask task1 = new ArraySumTask(this.array, this.begin, mid);
            ArraySumTask task2 = new ArraySumTask(this.array, mid + 1, end);

            task1.fork();
            task2.fork();

            long sum1 = task1.join();
            long sum2 = task2.join();

            sum = sum1 + sum2;
        }
        return sum;
    }
}

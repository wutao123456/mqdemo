package com.dlh.mw;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

    private ConcurrentLinkedQueue<Integer> workQueue;

    private ConcurrentHashMap<String,Integer> resultMap;

    public void setWorkQueue(ConcurrentLinkedQueue workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true){
            Integer i = workQueue.poll();
            if(i == null)break;
            int result = i*i*i;
            resultMap.put(i.toString(),result);
            System.out.println(i+"结果"+result);
        }
    }
}

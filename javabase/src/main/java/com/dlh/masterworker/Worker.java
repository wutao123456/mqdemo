package com.dlh.masterworker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable {

    private ConcurrentLinkedQueue<Task> workerQueue;

    private ConcurrentHashMap<String,Object> resultMap;

    public void setWorkerQueue(ConcurrentLinkedQueue<Task> workerQueue){
        this.workerQueue = workerQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true){
            Task input = workerQueue.poll();
            if(input == null)break;
            Object output = MyWorker.handle(input);
            resultMap.put(Integer.toString(input.getId()),output);
        }
    }
}

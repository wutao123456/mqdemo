package com.dlh.mw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    private ConcurrentLinkedQueue workQueue = new ConcurrentLinkedQueue();

    private HashMap<String,Thread> workers = new HashMap<>();

    private ConcurrentHashMap<String,Integer> resultMap = new ConcurrentHashMap<>();

    public Master(Worker worker,int workerCount) {
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);
        for(int i=0;i<workerCount;i++){
            workers.put("子节点"+i,new Thread(worker));
        }
    }

    public void submit(int i){
        workQueue.add(i);
    }

    public void execute(){
        for(Map.Entry<String,Thread> me:workers.entrySet()){
            me.getValue().start();
        }
    }

    public boolean isComplete(){
        for(Map.Entry<String,Thread> me:workers.entrySet()){
            if(me.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }

        return true;
    }

    public int getResult(){
        int result = 0;
        for(Map.Entry<String,Integer> me:resultMap.entrySet()){
            result += me.getValue().intValue();
        }
        return result;
    }
}

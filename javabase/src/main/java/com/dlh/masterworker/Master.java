package com.dlh.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    private ConcurrentLinkedQueue<Task> workerQueue = new ConcurrentLinkedQueue<>();

    private HashMap<String,Thread> workers = new HashMap<>();

    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<>();

    public ConcurrentLinkedQueue<Task> getWorkerQueue() {
        return workerQueue;
    }

    public HashMap<String, Thread> getWorkers() {
        return workers;
    }

    public Master(Worker worker, int workerCount) {
        worker.setWorkerQueue(this.workerQueue);
        worker.setResultMap(this.resultMap);
        for(int i=0;i<workerCount;i++){
            workers.put("子节点"+i,new Thread(worker));
        }
    }

    public void submit(Task task){
        workerQueue.add(task);
    }

    public void execute(){
        for(Map.Entry<String,Thread> entry:workers.entrySet()){
            entry.getValue().start();
        }
    }

    public boolean isComplete(){
        for(Map.Entry<String,Thread> entry:workers.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    public int getResult(){
        int result = 0;
        for(Map.Entry<String,Object> me:resultMap.entrySet()){
            result += (Integer)me.getValue();
        }

        return result;
    }
}

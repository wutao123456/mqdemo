package com.dlh.masterworker;

import java.util.Random;

public class MasterTest {

    public static void main(String[] args) {
        Master master = new Master(new MyWorker(),100);

        Random r = new Random();
        for(int i=0;i<10;i++){
            Task t = new Task();
            t.setId(i);
            t.setName("任务" + i);
            t.setPrice(r.nextInt(1000));
            master.submit(t);
        }

        System.out.println("任务数量"+master.getWorkerQueue().size());
        System.out.println("worker数量"+master.getWorkers().size());
        master.execute();

        long start = System.currentTimeMillis();

        while (true){
            if(master.isComplete()){
                int result = master.getResult();
                System.out.println("最终结果为"+result+"耗时"+(System.currentTimeMillis()-start));
                break;
            }
        }

    }
}

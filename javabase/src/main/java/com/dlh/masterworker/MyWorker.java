package com.dlh.masterworker;

public class MyWorker extends Worker {

    public static Object handle(Task input){
        Object output = null;

        try {
            Thread.sleep(200);
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}

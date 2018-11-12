package com.dlh.callback;

import java.util.concurrent.TimeUnit;

public class Notifier {

    public void execute(Caller caller,String msg) throws InterruptedException {
        System.out.println(msg);
        TimeUnit.SECONDS.sleep(2);
        caller.getCallBackListener().callBackNotify("bj");
    }
}

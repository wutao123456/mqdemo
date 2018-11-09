package com.dlh.callback;

public class Main {

    public static void main(String[] args) {

        Notifier notifier = new Notifier();
        Caller caller = new Caller();
        caller.setNotifier(notifier);
        caller.setQuestion("where is you");
        caller.setCallBackListener(new CallBackListener() {
            @Override
            public void callBackNotify(String msg) {
                System.out.println(msg);
            }
        });
        caller.call();
    }
}

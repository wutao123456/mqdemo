package com.dlh.callback;

public class Caller {

    private CallBackListener callBackListener;

    private Notifier notifier;

    private String question;

    public CallBackListener getCallBackListener() {
        return callBackListener;
    }

    public void setCallBackListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void call(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    notifier.execute(Caller.this,question);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

package com.dlh.mytest;

public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    //懒汉式单例模式线程安全问题解决
    private static LazySingleton getInstance(){
        if(instance==null){
            synchronized (LazySingleton.class){
                if(instance==null){
                    instance = new LazySingleton();
                }
            }
        }

        return instance;
    }
}

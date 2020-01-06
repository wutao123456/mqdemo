package com.dlh.mytest;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2020/1/6 23:03
 */
public class BestSingleton {

    public BestSingleton() {
    }

    private static class Inner{
        private static final BestSingleton INSTANCE = new BestSingleton();
    }

    public static BestSingleton getInstance(){
        return Inner.INSTANCE;
    }

}

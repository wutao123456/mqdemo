package com.wutao.future;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author wutao
 * @date 2020/1/3
 */
public class CallableTest {

    public static void main(String[] args) throws Exception{
        String result = "execute success";
        Callable<String> i = Executors.callable(new Task(),result);
        result = i.call();
        System.out.println(result);
    }
}

class Task implements Runnable{

    @Override
    public void run() {
        System.out.println("hello test");
    }
}

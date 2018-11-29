package com.dlh.proxy.dynamic.jdk;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 22:16
 */
public class RealSubject implements Subject {
    @Override
    public void action() {
        System.out.println("我是被代理类");
    }
}

package com.dlh.proxy.dynamic.jdk;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 22:20
 * JDK动态代理只能对实现了接口的类生成代理，而不能针对类。
 */
public class TestProxy {

    public static void main(String[] args) {
        MyInvocationHandler invocationHandler = new MyInvocationHandler();
        RealSubject subject = new RealSubject();
        Subject sub = (Subject) invocationHandler.blind(subject);
        sub.action();
    }
}

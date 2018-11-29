package com.dlh.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 22:17
 * JDK动态代理只能对实现了接口的类生成代理，而不能针对类。
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object obj;

    public Object blind(Object obj){
        this.obj = obj;
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnVal = method.invoke(obj,args);
        return returnVal;
    }
}

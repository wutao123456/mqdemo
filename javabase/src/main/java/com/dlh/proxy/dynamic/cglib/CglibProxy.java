package com.dlh.proxy.dynamic.cglib;

import com.dlh.proxy.dynamic.jdk.RealSubject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 22:25
 * CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法
 * 并覆盖其中方法实现增强，但是因为采用的是继承，所以该类或方法最好不要声明成final
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    private Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invokeSuper(o,objects);
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        RealSubject sub = (RealSubject) cglibProxy.getProxy(RealSubject.class);
        sub.action();
    }
}

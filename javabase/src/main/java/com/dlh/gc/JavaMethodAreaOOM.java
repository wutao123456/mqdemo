package com.dlh.gc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wutao
 * @date 2019/1/10
 */
public class JavaMethodAreaOOM {

    static class OOMObject{};

    /**
     * 方法区内存溢出测试
     * @param args
     */
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(obj,args);
                }
            });
            enhancer.create();
        }
    }
}

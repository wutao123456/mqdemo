package com.dlh.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/3 21:30
 * 客户一次性解决前置、后置、异常、返回通知
 */
public class GetUpAdvisor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("GetUpAdvisor before method");
//        System.out.println(methodInvocation.getThis().toString());
        Object result = methodInvocation.proceed();
        System.out.println("GetUpAdvisor after method");
        return result;
    }
}

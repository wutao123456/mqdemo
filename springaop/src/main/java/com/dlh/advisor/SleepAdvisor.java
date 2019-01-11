package com.dlh.advisor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/3 21:11
 */
public class SleepAdvisor implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("this is SleepAdvisor");
    }
}

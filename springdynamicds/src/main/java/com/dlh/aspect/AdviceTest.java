package com.dlh.aspect;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AdviceTest implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("睡觉前要脱衣服！");
    }
}

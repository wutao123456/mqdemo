package com.dlh.aspect;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/3 19:34
 */
public class TestAspect {

    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("the method "+methodName + " begins with "+args);
    }
}

package com.dlh.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/3 19:22
 * 使用@Order指定切面执行优先级,数值越小,优先级越高
 */
@Order(1)
@Component
@Aspect
public class ValidateAspect {

    @Before("execution(* com.dlh.entity.*.*(..)) || @annotation(com.dlh.annotation.MyAnnotatation)|| args(String)")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("the method "+methodName + " validate........ ");
    }
}

package com.dlh.aspect;

import com.dlh.annotation.DataTarget;
import com.dlh.un.ds.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

//@Aspect
@Component
public class ChooseDB {

//    @Before("execution(* com.dlh.mc.dao..*.*(..))")
    public void beforeMethod(JoinPoint point){
        System.out.println("切换数据库");
        //根据注解切换数据库
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataTarget dataTarget = method.getAnnotation(DataTarget.class);
        int corpRN = dataTarget.value();
        DataSourceContextHolder.setDataSourceType(String.format("core%S",corpRN));
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        System.out.println("the method "+methodName+" begin with "+ Arrays.asList(args));
    }
}

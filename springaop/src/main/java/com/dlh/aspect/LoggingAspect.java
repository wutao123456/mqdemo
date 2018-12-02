package com.dlh.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 21:11
 * 参考文档 http://jinnianshilongnian.iteye.com/blog/1415606
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * 第一个*号：表示返回类型，*号表示所有的类型。
     * 第二个*号：表示类名，*号表示所有的类(.*表示包下的所有类，而..*表示包、子孙包下的所有类。)
     * *(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     * @param joinPoint
     */
    @Before("execution(* com.dlh.entity.*.*(..)) || @annotation(com.dlh.annotation.MyAnnotatation)|| args(String)")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("the method "+methodName + " begins with "+args);
    }

    /**
     * target 匹配指定类
     * @param joinPoint
     * 此方法不能获取方法的返回值,返回通知可以获取返回值
     */
    @After("target(com.dlh.entity.TestEntity) && @annotation(com.dlh.annotation.MyAnnotatation)")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("the method "+methodName + " after with "+args);
    }

    /**
     * within 用于匹配指定类型内的方法执行
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "within(com.dlh.entity.TestEntity)",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("the method "+methodName + " afterReturning "+result);
    }

    @AfterThrowing(value = "execution(* com.dlh.entity.*.*(..)) ",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Exception e){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("the method "+methodName + " occurs exception: "+e);
    }

    @Around(value = "execution(* com.dlh.entity.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        try {
            System.out.println("方法执行开始前");
            result = proceedingJoinPoint.proceed();
            System.out.println("方法执行后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("异常通知");
        }
        System.out.println("后置通知");
        return result;
    }
}

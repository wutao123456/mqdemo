package com.dlh.annotation;

import java.lang.annotation.*;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 22:52
 * @Target 用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
 * @Retention 表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {

    String value() default "";

    enum Color{RED,BLUE,GREEN};

    Color color() default Color.RED;
}

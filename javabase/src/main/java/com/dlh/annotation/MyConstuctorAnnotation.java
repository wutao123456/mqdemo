package com.dlh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 23:15
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyConstuctorAnnotation {

    String value() default "";
}

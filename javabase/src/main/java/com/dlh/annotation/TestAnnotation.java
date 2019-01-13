package com.dlh.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 23:01
 */
@MyAnnotation(value = "test",color = MyAnnotation.Color.GREEN)
public class TestAnnotation {

    @MyFieldAnnotation(value = "testField")
    private int id;

    @MyConstuctorAnnotation(value = "testConstructor")
    public TestAnnotation() {
    }

    @MyMethodAnnotation(value = "testMethod")
    public void test(){

    }

    public static void main(String[] args)throws Exception {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        MyAnnotation myAnnotation = clazz.getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotation.value() +"======="+myAnnotation.color());

        Constructor<TestAnnotation> constructor = clazz.getConstructor(new Class[]{});
        MyConstuctorAnnotation constuctorAnnotation = constructor.getAnnotation(MyConstuctorAnnotation.class);
        System.out.println(constuctorAnnotation.value());


        Field field = clazz.getDeclaredField("id");
        MyFieldAnnotation fieldAnnotation = field.getAnnotation(MyFieldAnnotation.class);
        System.out.println(fieldAnnotation.value());

        Method method = clazz.getMethod("test");
        MyMethodAnnotation myMethodAnnotation = method.getAnnotation(MyMethodAnnotation.class);
        System.out.println(myMethodAnnotation.value());
        System.err.println(clazz.getSimpleName());
        System.err.println(clazz.getName() + " 被注解 "+clazz.isAnnotationPresent(MyAnnotation.class));
    }
}

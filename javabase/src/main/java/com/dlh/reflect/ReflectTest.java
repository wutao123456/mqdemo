package com.dlh.reflect;

import java.lang.reflect.Field;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 21:50
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception{
        //获取运行时类的四种方式
        //1.
        Class clazz = Person.class;

        //2.
        Person p = new Person();
        Class clazz2 = p.getClass();

        //3.
        String str = "com.dlh.reflect.Person";
        Class clazz3 = Class.forName(str);

        //4.
        ReflectTest r = new ReflectTest();
        ClassLoader loader = r.getClass().getClassLoader();
        Class clazz4 = loader.loadClass(str);
        Person p1 = (Person) clazz4.newInstance();
        Field[] fields = clazz4.getDeclaredFields();
        for(Field field:fields){
            //访问私有属性时，需设置开放权限
            field.setAccessible(true);
            System.out.println(field.getName()+" = "+field.get(p1));

        }
    }
}

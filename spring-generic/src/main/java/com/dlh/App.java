package com.dlh;

import com.dlh.generic.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class App {

    private List<String> list = new ArrayList<>();

    public static void main( String[] args ) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-generic.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        //获取泛型父类
        Type type = userService.getClass().getGenericSuperclass();
        Type[] types = ((ParameterizedType)type).getActualTypeArguments();
        System.out.println(Arrays.toString(types));
        userService.add();


        Field field = App.class.getDeclaredField("list");
        System.out.println(field.getGenericType());

    }
}

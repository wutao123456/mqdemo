package com.dlh;

import com.dlh.aspect.ChooseDB;
import com.dlh.mc.service.UserService;
import com.dlh.util.SpringContextHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationcontext.xml");
        UserService userService = (UserService) ctx.getBean("userService");
        UserService service = SpringContextHolder.getBean("userService");
        System.out.println("userService>>>>>>>>>"+service);
        ChooseDB chooseDB = (ChooseDB) ctx.getBean("chooseDB");
        //手动切换数据库
//        DataSourceContextHolder.setDataSourceType("core1");
        int count = userService.countUser(8);
        System.out.println(String.format("总记录数%S",count));
    }
}

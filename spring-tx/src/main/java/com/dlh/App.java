package com.dlh;

import com.dlh.entity.User;
import com.dlh.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 事务隔离级别演示
 */
public class App 
{
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) context.getBean("userService");
        User user = new User();
        user.setCn_user_id("12345678901");
        user.setCn_user_name("wutao_test");
        user.setCn_user_password("123456");
        userService.save(user);//测试声明式事务
        userService.test(user);//测试编程式事务
        userService.test1(user);//测试编程式事务(手动开启事务)
    }
}

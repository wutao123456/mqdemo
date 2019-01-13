package com.dlh.dubbo.test;

import com.dlh.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/12 21:31
 */
public class StubTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("stub.xml");
        ctx.start();
        System.out.println("consumer start");
        DemoService demoService = ctx.getBean(DemoService.class);
        demoService.getPermissions((long) 2);
    }
}

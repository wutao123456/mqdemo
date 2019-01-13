package com.dlh.dubbo.test;

import com.dlh.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/12 22:33
 */
public class MockTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mock.xml");
        ctx.start();
        System.out.println("consumer start");
        DemoService demoService = ctx.getBean(DemoService.class);
//        List<String> result = demoService.getPermissions((long) 2);
//        System.out.println(result);

        String re = demoService.mockTest(1);
        System.out.println(re);
    }
}

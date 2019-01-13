package com.dlh.dubbo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/12 17:36
 */
public class GenericTest {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("generic.xml");
        System.out.println(classPathXmlApplicationContext.getDisplayName()+"here");
        classPathXmlApplicationContext.start();
        System.out.println("服务已经启动...");
        System.in.read();
    }
}

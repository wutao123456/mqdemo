package com.dlh.dubbo.demo.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(classPathXmlApplicationContext.getDisplayName()+"here");
        classPathXmlApplicationContext.start();
        System.out.println("服务已经启动...");
        System.in.read();
    }
}

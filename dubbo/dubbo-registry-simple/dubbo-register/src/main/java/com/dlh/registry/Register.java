package com.dlh.registry;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @author wutao
 * @date 2020/1/13
 */
public class Register {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext registryContext = new ClassPathXmlApplicationContext("simple/registry.xml");
        registryContext.start();
        new CountDownLatch(1).await();
    }
}

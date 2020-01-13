package com.dlh;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

public class Provider {
    public static void main( String[] args ) throws Exception{
        ClassPathXmlApplicationContext registryContext = new ClassPathXmlApplicationContext("simple/provider-app.xml");
        registryContext.start();
        new CountDownLatch(1).await();
    }
}

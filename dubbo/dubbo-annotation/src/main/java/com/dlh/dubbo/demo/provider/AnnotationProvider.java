package com.dlh.dubbo.demo.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.CountDownLatch;

/**
 * @author wutao
 * @date 2020/1/9
 */
public class AnnotationProvider {

    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        applicationContext.start();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.dlh.dubbo.demo.impl")
    @PropertySource("classpath:/spring/dubbo-provider.properties")
    static public class ProviderConfiguration {

    }
}

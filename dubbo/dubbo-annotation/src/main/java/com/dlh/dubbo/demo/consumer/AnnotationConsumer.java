package com.dlh.dubbo.demo.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.rpc.RpcContext;
import com.dlh.dubbo.demo.action.AnnotationAction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author wutao
 * @date 2020/1/9
 */
public class AnnotationConsumer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        applicationContext.start();
        final AnnotationAction annotationAction = (AnnotationAction) applicationContext.getBean("annotationAction",AnnotationAction.class);
        RpcContext.getContext().setAttachment("index","12345");
        List<String> result = annotationAction.test();
        System.out.println("result: "+result);
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.dlh.dubbo.demo.action")
    @PropertySource("classpath:/spring/dubbo-consumer.properties")
    @ComponentScan(value = {"com.dlh.dubbo.demo.action"})
    static public class ConsumerConfiguration {

    }
}

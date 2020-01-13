package com.dlh;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dlh.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Consumer
{
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext localContext = new ClassPathXmlApplicationContext("simple/consumer-app.xml");
        localContext.start();
        DemoService greetingsService = (DemoService) localContext.getBean("greetingsService");
        RpcContext.getContext().setAttachment("index","test");
        List<String> result = greetingsService.getPermissions(1L);
        System.out.println("result: " + result);
    }
}

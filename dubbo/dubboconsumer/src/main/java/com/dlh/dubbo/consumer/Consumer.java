package com.dlh.dubbo.consumer;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.dlh.dubbo.callback.CallbackListener;
import com.dlh.dubbo.callback.CallbackService;
import com.dlh.dubbo.demo.DemoService;
import com.dlh.dubbo.notify.NotifyImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class Consumer {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("consumer.xml");
        ctx.start();
        System.out.println("consumer start");
        DemoService demoService = ctx.getBean(DemoService.class);
        EchoService echoService = (EchoService) demoService;
        //回声测试,用于检测服务是否可用
        String status = (String) echoService.$echo("OK");

        // 本端是否为消费端，这里会返回true
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        // 获取最后一次调用的提供方IP地址
        String serverIP = RpcContext.getContext().getRemoteHost();
        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        String application = RpcContext.getContext().getUrl().getParameter("application");
        System.err.println(isConsumerSide +": "+serverIP + ": " +application);
        System.out.println(status);
        System.out.println("consumer");
        System.out.println(demoService.getPermissions(1L));


        //测试回调
        CallbackService callbackService = ctx.getBean(CallbackService.class);
        callbackService.addListener("test", new CallbackListener() {
            public void changed(String msg) {
                System.out.println("callback: "+msg);
            }
        });

        //测试事件通知
        NotifyImpl notify = (NotifyImpl) ctx.getBean("notifyService");
        Map<Long, List<String>> rets = notify.rets;
    }
}

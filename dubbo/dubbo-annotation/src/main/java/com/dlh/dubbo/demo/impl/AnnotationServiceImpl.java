package com.dlh.dubbo.demo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.rpc.RpcContext;
import com.dlh.dubbo.demo.DemoService;
import com.dlh.dubbo.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @date 2020/1/9
 */
@Service
public class AnnotationServiceImpl implements DemoService {
    public List<String> getPermissions(Long id) {
        String index = RpcContext.getContext().getAttachment("index");
        System.out.println("隐式参数index: "+index);
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }

    public User queryUserById(int id) {
        return null;
    }



    @Configuration
    @EnableDubbo(scanBasePackages = "com.dlh.dubbo.demo.impl")
    @PropertySource("classpath:/spring/dubbo-provider.properties")
    static public class ProviderConfiguration {

    }
}

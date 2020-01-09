package com.dlh.api.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.dlh.dubbo.demo.DemoService;

import java.util.List;

/**
 * @author wutao
 * @date 2020/1/9
 */
public class Consumer {

    public static void main(String[] args) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("java.api.consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://192.168.2.215:2181");

        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<DemoService>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(DemoService.class);

        DemoService demoService = referenceConfig.get();
        List<String> result = demoService.getPermissions(1L);
        System.out.println("result: "+result);
    }
}

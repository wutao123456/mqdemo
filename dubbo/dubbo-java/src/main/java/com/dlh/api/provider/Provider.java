package com.dlh.api.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.dlh.demo.impl.DemoServiceImpl;
import com.dlh.dubbo.demo.DemoService;

import java.io.IOException;

/**
 * @author wutao
 * @date 2020/1/9
 */
public class Provider {

    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("java.api.provider");
        //是否开启qos
        applicationConfig.setQosPort(33333);
        applicationConfig.setQosEnable(true);
        applicationConfig.setQosAcceptForeignIp(true);

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://192.168.2.215:2181");
        registryConfig.setUsername("admin");
        registryConfig.setPassword("123456");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);

        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<DemoService>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);

        serviceConfig.export();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

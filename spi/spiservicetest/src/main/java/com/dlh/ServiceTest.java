package com.dlh;

import com.dlh.service.Service;

import java.util.ServiceLoader;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 20:55
 */
public class ServiceTest {

    public static void main(String[] args) {
        ServiceLoader<Service> services = ServiceLoader.load(Service.class);
        for(Service service:services){
            service.say();
        }
    }
}

package com.dlh.dubbo.demo.impl;

import com.dlh.dubbo.demo.service.EasyCommonService;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/12 17:32
 */
public class EasyCommonServiceImpl implements EasyCommonService {
    public String helloService(String name) {
        System.out.println("name is "+ name);
        return "hello " + name;
    }
}

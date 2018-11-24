package com.dlh.util;

import org.springframework.beans.factory.InitializingBean;

public class TestBean implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        System.out.println(">>>>>>>>>>>>>>>初始化后开始自己的方法");
    }
}

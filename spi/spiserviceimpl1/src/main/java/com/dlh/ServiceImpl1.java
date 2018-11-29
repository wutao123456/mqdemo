package com.dlh;

import com.dlh.service.Service;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 20:43
 */
public class ServiceImpl1 implements Service {
    @Override
    public void say() {
        System.out.println("say hello from ServiceImpl1");
    }
}

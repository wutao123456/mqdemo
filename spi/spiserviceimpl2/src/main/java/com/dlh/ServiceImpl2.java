package com.dlh;

import com.dlh.service.Service;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 20:46
 */
public class ServiceImpl2 implements Service {
    @Override
    public void say() {
        System.out.println("say hello from ServiceImpl2");
    }
}

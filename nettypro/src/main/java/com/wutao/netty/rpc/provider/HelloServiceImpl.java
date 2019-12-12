package com.wutao.netty.rpc.provider;

import com.wutao.netty.rpc.publicinterface.HelloService;

/**
 * @author wutao
 * @date 2019/12/11
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        return msg != null ? msg + " -----> I am fine." : "I am fine.";
    }
}

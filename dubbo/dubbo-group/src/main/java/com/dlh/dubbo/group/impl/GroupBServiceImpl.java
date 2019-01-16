package com.dlh.dubbo.group.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dlh.dubbo.group.api.GroupService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/14 22:44
 */
public class GroupBServiceImpl implements GroupService {

    @Override
    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext
                .getContext().getRemoteAddress() + "in groupB");
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress() + "group B";
    }
}

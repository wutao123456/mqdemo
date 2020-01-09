package com.dlh.demo.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dlh.dubbo.demo.DemoService;
import com.dlh.dubbo.model.User;

import java.util.ArrayList;
import java.util.List;

public class DemoServiceImpl implements DemoService {

    public List<String> getPermissions(Long id) {
        String index = RpcContext.getContext().getAttachment("index");
        System.out.println("隐式参数index: "+index);
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }

    public User queryUserById(int i) {
        return null;
    }

    public String mockTest(int id) {
        return null;
    }
}

package com.dlh.dubbo.demo.impl;

import com.dlh.dubbo.demo.DemoService;
import com.dlh.dubbo.model.User;

import java.util.ArrayList;
import java.util.List;

public class DemoServiceImpl implements DemoService{

    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }

    public User queryUserById(int i) {
        return null;
    }
}

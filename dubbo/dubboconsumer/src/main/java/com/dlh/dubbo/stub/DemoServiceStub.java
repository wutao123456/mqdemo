package com.dlh.dubbo.stub;

import com.dlh.dubbo.demo.DemoService;
import com.dlh.dubbo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @date 2018/11/13
 */
public class DemoServiceStub implements DemoService {

    private DemoService demoService;

    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    public List<String> getPermissions(Long id) {
        List<String> resultList = new ArrayList<String>();
        if(id == 1){
            resultList = demoService.getPermissions(id);
        }
        return resultList;
    }

    public User queryUserById(int id) {
        return null;
    }
}

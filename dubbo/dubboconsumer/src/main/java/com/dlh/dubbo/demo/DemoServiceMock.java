package com.dlh.dubbo.demo;

import com.dlh.dubbo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/12 22:31
 */
public class DemoServiceMock implements DemoService {
    public List<String> getPermissions(Long id) {
        List<String> result = new ArrayList<String>();
        result.add("容错数据");
        return result;
    }

    public User queryUserById(int id) {
        return null;
    }

    public String mockTest(int id) {
        return null;
    }
}

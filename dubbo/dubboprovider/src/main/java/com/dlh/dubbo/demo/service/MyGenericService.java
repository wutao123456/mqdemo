package com.dlh.dubbo.demo.service;

import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/12 19:43
 */
public class MyGenericService implements GenericService {

    public Object $invoke(String methodName, String[] strings, Object[] objects) throws GenericException {
        if("sayHello".equals(methodName)){
            return " Welcome "+objects[0];
        }
        return "test";
    }
}

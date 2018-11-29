package com.dlh.proxy.staticproxy;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 22:38
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return " hello " + name;
    }
}

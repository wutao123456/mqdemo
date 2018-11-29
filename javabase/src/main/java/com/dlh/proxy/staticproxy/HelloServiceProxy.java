package com.dlh.proxy.staticproxy;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 22:39
 */
public class HelloServiceProxy implements HelloService{

    private HelloService helloService;

    public HelloServiceProxy(HelloService helloService) {
        this.helloService = helloService;
    }


    @Override
    public String hello(String name) {
        System.out.println("预处理");
        String result = helloService.hello(name);
        System.out.println(result);
        System.out.println("后处理");
        return result;
    }
}

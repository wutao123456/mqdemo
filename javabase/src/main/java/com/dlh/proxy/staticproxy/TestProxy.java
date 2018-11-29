package com.dlh.proxy.staticproxy;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/29 22:41
 * jdk静态代理,每次都要手动创建一个被代理对象(被代理对象较多时，工作量大，不好维护)
 */
public class TestProxy {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        HelloServiceProxy proxy = new HelloServiceProxy(helloService);
        proxy.hello("test");
    }
}

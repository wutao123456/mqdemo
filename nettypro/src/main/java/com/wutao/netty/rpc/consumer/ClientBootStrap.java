package com.wutao.netty.rpc.consumer;

import com.wutao.netty.rpc.publicinterface.HelloService;

/**
 * @author wutao
 * @date 2019/12/11
 */
public class ClientBootStrap {

    private static final String providerName = "HelloService#hello#";

    public static void main(String[] args)throws Exception {
        RpcConsumer rpcConsumer = new RpcConsumer();
        HelloService helloService = (HelloService) rpcConsumer.createProxy(HelloService.class,providerName);
        for(;;){
            Thread.sleep(1000);
            System.out.println(helloService.hello("are you ok"));
        }
    }
}

package com.wutao.netty.rpc.provider;

/**
 * @author wutao
 * @date 2019/12/11
 */
public class NettyServerBootstrap {

    public static void main(String[] args) {
        NettyServer.startServer("localhost",9999);
    }
}

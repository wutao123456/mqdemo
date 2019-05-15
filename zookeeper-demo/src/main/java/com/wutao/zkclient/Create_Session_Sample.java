package com.wutao.zkclient;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 16:28
 */
public class Create_Session_Sample {

    public static void main(String[] args) throws IOException,InterruptedException {
        ZkClient zkClient = new ZkClient("192.168.0.200:2181",5000);
        System.out.println("Zookeeper session established");
    }
}

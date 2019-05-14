package com.wutao.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 17:00
 */
public class Delete_Node_Sample {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.0.200:2181",5000);
        String path = "/zk-book";
//        zkClient.delete(path);
        zkClient.deleteRecursive(path);
    }
}

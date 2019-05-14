package com.wutao.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 17:19
 */
public class Get_Data_Sample {

    public static void main(String[] args) throws Exception{
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient("192.168.0.200:2181",5000);
        zkClient.createEphemeral(path,"123");
        System.out.println("存在/zk-book "+zkClient.exists(path));
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("Node "+s+" changed,new data: "+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("Node "+s+" deleted");
            }
        });

        System.out.println(zkClient.readData(path));
        zkClient.writeData(path,"456");
        Thread.sleep(1000);
        zkClient.delete(path);
        System.out.println("存在/zk-book "+zkClient.exists(path));
        Thread.sleep(Integer.MAX_VALUE);
    }
}

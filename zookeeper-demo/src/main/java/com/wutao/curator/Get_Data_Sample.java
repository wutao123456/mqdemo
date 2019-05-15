package com.wutao.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 18:27
 */
public class Get_Data_Sample {

    public static void main(String[] args) throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
//        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.0.200:2181",5000,3000,retryPolicy);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.0.200:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        System.out.println(new String(client.getData().forPath("/zk-base/zk-book")));
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/zk-base/zk-book");
        System.out.println(stat);

    }
}

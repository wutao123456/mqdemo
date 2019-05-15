package com.wutao.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 17:52
 */
public class Create_Session_Sample {

    public static void main(String[] args) throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
//        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.0.200:2181",5000,3000,retryPolicy);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.0.200:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("zk-base")
                .build();
        client.start();
        client.create().forPath("/zk-book","123".getBytes());
        Thread.sleep(Integer.MAX_VALUE);
    }
}

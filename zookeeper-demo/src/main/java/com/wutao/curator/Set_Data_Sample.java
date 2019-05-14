package com.wutao.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 18:31
 */
public class Set_Data_Sample {

    public static void main(String[] args) throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.0.200:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        String path = "/zk-base/zk-book";
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        System.out.println("Success set node for :"+path +", new version " +client.setData().withVersion(stat.getVersion()).forPath(path,"456".getBytes()).getVersion());
        try {
            client.setData().withVersion(stat.getVersion()).forPath(path,"789".getBytes());
        } catch (Exception e) {
            System.out.println("Fail set node due to "+e.getMessage());
        }
    }
}

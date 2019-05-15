package com.wutao.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 21:01
 */
public class NodeCache_Sample {

    static String path = "/zk-book";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.0.200:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();

    public static void main(String[] args) throws Exception{
        client.start();
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path,"init".getBytes());
        final NodeCache nodeCache = new NodeCache(client,path,false);
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Node data update,new data: "+new String(nodeCache.getCurrentData().getData()));
            }
        });

        client.setData().forPath(path,"test".getBytes());
        Thread.sleep(1000);
        client.delete().deletingChildrenIfNeeded();
        Thread.sleep(Integer.MAX_VALUE);
    }
}

package com.wutao.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 20:41
 */
public class Create_Node_Backgroud_Sample {

    static String path = "/zk-book";

    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.0.200:2181")
            .sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .build();
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    static ExecutorService tp = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception{
        client.start();
        System.out.println("Main thread: "+Thread.currentThread().getName());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("event[code: "+ curatorEvent.getResultCode()+",type: "+curatorEvent.getType()+"]");

                System.out.println("Thread of processResult: "+Thread.currentThread().getName());

                countDownLatch.countDown();
            }

        },tp).forPath(path,"init".getBytes());

        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                System.out.println("event[code: "+ curatorEvent.getResultCode()+",type: "+curatorEvent.getType()+"]");

                System.out.println("Thread of processResult: "+Thread.currentThread().getName());

                countDownLatch.countDown();
            }

        }).forPath(path,"init".getBytes());

        countDownLatch.await();
        tp.shutdown();
    }
}

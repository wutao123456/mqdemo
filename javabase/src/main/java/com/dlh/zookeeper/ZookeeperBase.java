package com.dlh.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZookeeperBase {

    /** zookeeper地址 */
    static final String CONNECT_ADDR = "192.168.2.204:2181";
    /** session超时时间 */
    static final int SESSION_OUTTIME = 1000;//ms
    /** 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号 */
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                Event.KeeperState keeperState = watchedEvent.getState();
                Event.EventType eventType = watchedEvent.getType();
                if(Event.KeeperState.SyncConnected == keeperState){
                    if(Event.EventType.None == eventType){
                        connectedSemaphore.countDown();
                        System.out.println("zookeeper建立连接");
                    }
                }
            }
        });

        connectedSemaphore.await();
        System.out.println("....");

        zooKeeper.close();
    }
}

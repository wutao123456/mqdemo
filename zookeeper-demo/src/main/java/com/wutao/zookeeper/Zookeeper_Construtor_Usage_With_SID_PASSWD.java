package com.wutao.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 11:56
 */
public class Zookeeper_Construtor_Usage_With_SID_PASSWD implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.0.202:2181",5000,new Zookeeper_Construtor_Usage_With_SID_PASSWD());
        countDownLatch.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] passwd = zooKeeper.getSessionPasswd();
        zooKeeper = new ZooKeeper("192.168.0.202:2181",5000,new Zookeeper_Construtor_Usage_With_SID_PASSWD(),1L,"test".getBytes());
        zooKeeper = new ZooKeeper("192.168.0.202:2181",5000,new Zookeeper_Construtor_Usage_With_SID_PASSWD(),sessionId,passwd);
        Thread.sleep(Integer.MAX_VALUE);

    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: "+watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }
}

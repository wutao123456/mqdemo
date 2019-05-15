package com.wutao.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 15:45
 */
public class Zookeeper_SetData_API_Sync_Usage implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args)throws Exception {
        String path="/zk-book";
        zooKeeper = new ZooKeeper("192.168.0.200:2181",5000,new Zookeeper_SetData_API_Sync_Usage());
        countDownLatch.await();
        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.getData(path,true,null);

        Stat stat = zooKeeper.setData(path,"456".getBytes(),-1);
        System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());

        Stat stat2 = zooKeeper.setData(path,"456".getBytes(),stat.getVersion());
        System.out.println(stat2.getCzxid()+","+stat2.getMzxid()+","+stat2.getVersion());

        try {
            zooKeeper.setData(path,"456".getBytes(), stat.getVersion());
        } catch (KeeperException e) {
            System.out.println("Error: " + e.code() +","+e.getMessage());
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
            }
        }
    }
}

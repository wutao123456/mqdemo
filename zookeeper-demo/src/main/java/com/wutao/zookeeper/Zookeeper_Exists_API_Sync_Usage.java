package com.wutao.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 16:17
 */
public class Zookeeper_Exists_API_Sync_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args)throws Exception {
        String path="/zk-book";
        zooKeeper = new ZooKeeper("192.168.0.200:2181",5000,new Zookeeper_Exists_API_Sync_Usage());
        countDownLatch.await();
        zooKeeper.exists(path,true);
        zooKeeper.create(path,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.setData(path,"123".getBytes(),-1);

        zooKeeper.create(path+"/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        zooKeeper.delete(path+"/c1",-1);
        zooKeeper.delete(path,-1);

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    countDownLatch.countDown();
                }else if(Event.EventType.NodeCreated == watchedEvent.getType()){
                    System.out.println("Node(" +watchedEvent.getPath() +")Created");
                    zooKeeper.exists(watchedEvent.getPath(),true);
                }else if(Event.EventType.NodeDataChanged == watchedEvent.getType()){
                    System.out.println("Node(" +watchedEvent.getPath() +")DataChanged");
                    zooKeeper.exists(watchedEvent.getPath(),true);
                }else if(Event.EventType.NodeDeleted == watchedEvent.getType()){
                    System.out.println("Node(" +watchedEvent.getPath() +")Deleted");
                    zooKeeper.exists(watchedEvent.getPath(),true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.wutao.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.awt.event.WindowAdapter;
import java.util.concurrent.CountDownLatch;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/7 15:33
 */
public class Zookeeper_GetData_API_Sync_Usage implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception{
        String path="/zk-book";
        zooKeeper = new ZooKeeper("192.168.0.200:2181",5000,new Zookeeper_GetData_API_Sync_Usage());
        countDownLatch.await();
        zooKeeper.create(path,"123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        System.out.println(new String(zooKeeper.getData(path,true,stat)));
        System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());

        zooKeeper.setData(path,"123".getBytes(),-1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            if(Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                countDownLatch.countDown();
            }else if(watchedEvent.getType() == Event.EventType.NodeDataChanged){
                try {
                    System.out.println(new String(zooKeeper.getData(watchedEvent.getPath(),true,stat)));
                    System.out.println(stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

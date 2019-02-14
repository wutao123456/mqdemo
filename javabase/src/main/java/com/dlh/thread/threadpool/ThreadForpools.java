package com.dlh.thread.threadpool;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/14 22:21
 */
public class ThreadForpools implements Runnable{

    private Integer index;

    public ThreadForpools(Integer index) {
        this.index = index;
    }

    @Override
    public void run() {
        try{
            System.out.println("开始处理线程！！！");
            Thread.sleep(index*100);
            System.out.println("我的线程标识是："+this.toString());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

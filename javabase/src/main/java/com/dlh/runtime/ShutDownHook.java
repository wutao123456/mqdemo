package com.dlh.runtime;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * @author wutao
 * @date 2019/12/20
 * shutdownHook是一种特殊的结构，它允许开发人员插入JVM关闭时执行的一段代码。这种情况在我们需要做特殊清理操作的情况下很有用
 */
public class ShutDownHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try(FileWriter fw = new FileWriter("hook.log")){
                fw.write("完成销毁动作,回收内存"+new Date().toString());
                System.out.println("退出程序");
            }catch (IOException e){
                e.printStackTrace();
            }
        }));

        IntStream.range(0,9).forEach(i->{
            try {
                System.out.println("正在执行...");
                Thread.sleep(2*1000L);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
    }
}

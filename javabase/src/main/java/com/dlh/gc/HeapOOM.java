package com.dlh.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @date 2019/1/10
 */
public class HeapOOM {

    static class OOMObject{};

    /**
     * 设置VM参数 -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 堆内存溢出测试
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}

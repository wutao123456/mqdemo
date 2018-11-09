package com.dlh.designpattern.flyweight;

import java.util.HashMap;

/**
 * 享元模式
 * create by wutao on 2018/8/29
 */
public class FlyWeightFactory {

    private HashMap<Object,Flyweight> dataMap;

    public FlyWeightFactory(){
        dataMap = new HashMap<>();
    }

    public Flyweight getFlyweight(Object key){
        if(dataMap.containsKey(key)){
            return dataMap.get(key);
        }else{
            Flyweight flyweight = new Flyweight(key);
            dataMap.put(key,flyweight);
            return flyweight;
        }
    }
}

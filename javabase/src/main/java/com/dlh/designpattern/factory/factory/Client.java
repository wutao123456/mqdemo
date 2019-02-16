package com.dlh.designpattern.factory.factory;

import com.dlh.designpattern.factory.simplefactory.Operation;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:46
 * 工厂模式
 */
public class Client {

    public static void main(String[] args) throws Exception{
        AddFactory addFactory = new AddFactory();
        SubFactory subFactory = new SubFactory();
        Operation add = addFactory.createOperation();
        Operation sub = subFactory.createOperation();
        System.out.println(add.getResult(1,1));
        System.out.println(sub.getResult(1,1));
    }
}

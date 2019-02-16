package com.dlh.designpattern.factory.simplefactory;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:40
 */
public class Client {

    public static void main(String[] args) throws Exception{
        Operation add = EasyFactory.createOperation("+");
        Operation sub = EasyFactory.createOperation("-");
        System.out.println(add.getResult(1,1));
        System.out.println(sub.getResult(1,1));
    }
}

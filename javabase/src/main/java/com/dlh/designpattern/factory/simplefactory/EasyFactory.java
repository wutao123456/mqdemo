package com.dlh.designpattern.factory.simplefactory;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 13:38
 * 简单工厂模式
 */
public class EasyFactory {

    public static Operation createOperation(String operation){
        Operation operationObj = null;
        switch (operation){
            case "+":
                operationObj = new Add();
                break;
            case "-":
                operationObj = new Sub();
                break;
        }

        return operationObj;
    }
}

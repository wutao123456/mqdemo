package com.dlh.designpattern.template;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 14:42
 */
public class WCar extends CarModel {
    @Override
    void start() {
        System.out.println("大众启动");
    }

    @Override
    void stop() {
        System.out.println("大众停止");
    }
}

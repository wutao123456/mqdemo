package com.dlh.designpattern.template;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 14:43
 */
public class OCar extends CarModel {
    @Override
    void start() {
        System.out.println("奥迪启动");
    }

    @Override
    void stop() {
        System.out.println("奥迪停止");
    }
}

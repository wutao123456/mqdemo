package com.dlh.designpattern.template;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/2/16 14:41
 * 模板方法模式
 */
public abstract class CarModel {

    abstract void start();

    abstract void stop();

    final public void excet(){
        this.start();
        this.stop();
    }
}

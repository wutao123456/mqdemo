package com.dlh.designpattern.decorator;

public class ManDecoratorB extends Decorator {

    @Override
    public void eat() {
        System.out.println("打印日志开始");
        super.eat();
        System.out.println("打印日志结束");
    }
}

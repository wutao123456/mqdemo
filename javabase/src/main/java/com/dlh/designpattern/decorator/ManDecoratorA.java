package com.dlh.designpattern.decorator;

public class ManDecoratorA extends Decorator {

    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }

    public void reEat(){
        System.out.println("eat price onece more");
    }
}

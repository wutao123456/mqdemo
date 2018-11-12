package com.dlh.designpattern.decorator;

public class Test {
    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA a = new ManDecoratorA();
        ManDecoratorB b = new ManDecoratorB();
        a.setPerson(man);
        b.setPerson(man);
        a.eat();
        b.eat();
    }
}

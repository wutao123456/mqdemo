package com.dlh.designpattern.bridge;

/**
 * create by wutao on 2018/8/29
 */
public class BridgeTest {

    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());
        redCircle.draw();
        greenCircle.draw();
    }
}






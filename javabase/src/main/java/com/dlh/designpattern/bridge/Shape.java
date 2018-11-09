package com.dlh.designpattern.bridge;

/**
 * create by wutao on 2018/8/29
 */
public abstract class Shape {

    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

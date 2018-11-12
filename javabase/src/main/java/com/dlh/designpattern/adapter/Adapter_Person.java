package com.dlh.designpattern.adapter;

/**
 * 类适配
 */
public class Adapter_Person extends Person implements Target_Person {
    @Override
    public void sayFrench() {
        System.out.println("Person can say French");
    }

    @Override
    public void sayJapanese() {
        System.out.println("Person can say Japanese");
    }
}

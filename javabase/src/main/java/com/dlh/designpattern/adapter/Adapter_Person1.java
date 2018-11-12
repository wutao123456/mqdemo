package com.dlh.designpattern.adapter;

/**
 * 对象适配
 */
public class Adapter_Person1 implements Target_Person {

    private Person person;

    public Adapter_Person1(Person person) {
        this.person = person;
    }

    @Override
    public void sayEnglish() {
        this.person.sayEnglish();
    }

    @Override
    public void sayFrench() {
        System.out.println("Person1 can say French!");
    }

    @Override
    public void sayJapanese() {
        System.out.println("Person1 can say japanese!");
    }
}

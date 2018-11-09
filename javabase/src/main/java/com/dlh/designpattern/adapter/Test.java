package com.dlh.designpattern.adapter;

public class Test {

    public static void main(String[] args) {
        Target_Person target_person = new Adapter_Person();
        target_person.sayFrench();
        target_person.sayJapanese();


        System.err.println(">>>>>>>>>>>>>>>>>");
        Target_Person target_person1 = new Adapter_Person1(new Person());
        target_person1.sayEnglish();
        target_person1.sayFrench();
        target_person1.sayJapanese();
    }
}

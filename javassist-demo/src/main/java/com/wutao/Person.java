package com.wutao;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2020/2/17 15:50
 */
public class Person {

    private String name = "xiaoming";

    public void setName(String var1) {
        this.name = var1;
    }

    public String getName() {
        return this.name;
    }

    public Person() {
        this.name = "xiaohong";
    }

    public Person(String var1) {
        this.name = var1;
    }

    public void printName() {
        System.out.println(this.name);
    }
}

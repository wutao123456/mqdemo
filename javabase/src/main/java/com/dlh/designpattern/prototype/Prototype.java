package com.dlh.designpattern.prototype;

/**
 * 原型模式,实现浅拷贝(深拷贝可以使用对象序列化)
 */
public class Prototype implements Cloneable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "Prototype{" +
//                "name='" + name + '\'' +
//                '}';
//    }

    @Override
    protected Object clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        Prototype p = new Prototype();
        p.setName("wt");
        Prototype p1 = (Prototype) p.clone();
        System.out.println(p);
        System.out.println(p1);
    }
}

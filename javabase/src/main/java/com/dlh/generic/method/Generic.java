package com.dlh.generic.method;

import com.dlh.reflect.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 14:09
 */


public class Generic<T>{
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    /**
     * 泛型方法
     * @param container
     * @param <T>
     * @return
     */
    public static <T> T showKeyValue(Generic<T> container){
        System.out.println(container.getKey());
        T test = container.getKey();
        return test;
    }

    public void show1(T t){
        System.out.println(t.toString());
    }

    //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
    //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，
    // 编译器也能够正确识别泛型方法中识别的泛型。
    public <E> void show2(E e){
        System.out.println(e.toString());
    }

    public <T> void printMsg(T... msg){
        for(T t:msg){
            System.out.println(t.toString());
        }
    }



    public static void main(String[] args) {
        Generic<String> stringGeneric = new Generic("test1");
//        String str = showKeyValue(stringGeneric);
//        System.out.println("get value >>>>>>>>>"+str);

        Person p = new Person();
        //person与stringGeneric传入的泛型类型不一致,但show2泛型方法声明了另一种泛型类型E,所以可以通过
        stringGeneric.show2(p);
        stringGeneric.printMsg("111",222,"aaaa","2323.4",55.55);

        List<?>[] lsa = new List<?>[10];
    }
}




package com.dlh.generic.classtest;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 13:49
 * 泛型类的使用
 */
public class Generic<T> {

    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }


    /**
     * 泛型通配符
     * @param t
     */
    public static void showKeyValue(Generic<?> t){
        System.out.println(t.getKey());
    }


    public static void main(String[] args) {
        Generic<Integer> integerGeneric = new Generic<>(123456);
        Generic<String> stringGeneric = new Generic<>("test");
        showKeyValue(integerGeneric);
        showKeyValue(stringGeneric);
//        System.out.println(integerGeneric.getKey());
//        System.out.println(stringGeneric.getKey());
    }

}

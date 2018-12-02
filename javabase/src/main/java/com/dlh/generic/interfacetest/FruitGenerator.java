package com.dlh.generic.interfacetest;

import java.util.Random;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 13:53
 */
public class FruitGenerator implements Generator<String> {

    private String[] fruits = new String[]{"apple","banana","bear"};
    @Override
    public String next() {
        Random random = new Random();
        return fruits[random.nextInt(3)];
    }

    public static void main(String[] args) {
        FruitGenerator fruitGenerator = new FruitGenerator();
        System.out.println(fruitGenerator.next());
    }
}

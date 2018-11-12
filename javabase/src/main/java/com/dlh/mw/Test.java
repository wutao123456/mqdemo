package com.dlh.mw;

public class Test {

    public static void main(String[] args) {
        int result = 0;
        long start = System.currentTimeMillis();
        for(int i=1;i<=10000;i++){
            result += (i*i*i);
        }

        long end = System.currentTimeMillis();
        System.out.println("最终结果为"+result+"耗时"+(end-start));
    }
}

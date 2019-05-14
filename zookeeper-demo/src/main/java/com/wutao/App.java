package com.wutao;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        for(Integer i:list){
            System.out.println(i);
        }
    }
}

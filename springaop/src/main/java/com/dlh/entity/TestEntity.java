package com.dlh.entity;

import com.dlh.annotation.MyAnnotatation;
import org.springframework.stereotype.Service;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 21:10
 */
@Service
public class TestEntity {

    public int add(int a,int b){
        System.out.println("execute amethod add");
        return a + b;
    }

    @MyAnnotatation
    public void testAnnotation(){
        System.out.println("just test Annotation");
    }

    public void testArgs(String str){
        System.out.println("just test args "+ str);
    }

    public void testException(){
        throw new RuntimeException("exception");
    }
}

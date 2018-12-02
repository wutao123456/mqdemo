package com.dlh;


import com.dlh.entity.TestEntity;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("aspect.xml");
        TestEntity entity = (TestEntity) context.getBean("testEntity");
//        int result = entity.add(1,2);
//        System.out.println("result = "+result);
//        entity.testAnnotation();
//        entity.testArgs("hehe");
        entity.testException();
    }
}

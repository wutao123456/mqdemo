package com.dlh;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //第一种方法:继承quartzBean
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-quartz.xml");


        //第二种方法:自定义job,注入到MethodInvokingJobDetailFactoryBean
//        ClassPathXmlApplicationContext classPathXmlApplicationContext2 = new ClassPathXmlApplicationContext("spring-quartz3.xml");

        //测试jobdetail中注入service (main方法启动无效,必须在web容器中)
        ClassPathXmlApplicationContext classPathXmlApplicationContext3 = new ClassPathXmlApplicationContext("spring-quartz3.xml");
        System.out.println( "spring容器启动" );
    }
}

package com.dlh.dubbo.group.merge;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/15 21:46
 */
public class MergeProvider2 {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/merge-provider2.xml"});
        context.start();
        System.in.read();
    }
}

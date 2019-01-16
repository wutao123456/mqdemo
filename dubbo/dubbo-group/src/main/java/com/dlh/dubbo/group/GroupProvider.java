package com.dlh.dubbo.group;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/14 22:49
 */
public class GroupProvider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/group-provider.xml"});
        context.start();

        System.in.read(); // press any key to exit
    }
}

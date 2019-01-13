package com.dlh.dubbo.test;

import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/12 17:40
 */
public class DubboConsumerGenericService {

    public static void main(String[] args) {
        /////////////////Spring泛化调用/////////
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "generic.xml");
        context.start();

        GenericService easyCommonService = (GenericService) context.getBean("easyCommonService");
        Object result = easyCommonService.$invoke("helloService", new String[] { "java.lang.String" }, new Object[] { "wutao" });
        System.out.println(result);

        GenericService demoService = (GenericService) context.getBean("demoService");
        Object result1 = demoService.$invoke("sayHello", new String[] { "java.lang.String" }, new Object[] { "wutao" });
        System.out.println(result1);
    }
}

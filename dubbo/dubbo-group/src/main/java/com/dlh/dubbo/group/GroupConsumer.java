package com.dlh.dubbo.group;

import com.dlh.dubbo.group.api.GroupService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/14 22:50
 */
public class GroupConsumer {

    public static void main(String[] args) {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/group-consumer.xml"});
        context.start();
        GroupService groupAService = (GroupService) context.getBean("groupAService"); // get remote service proxy
        GroupService groupBService = (GroupService)context.getBean("groupBService");

        while (true) {
            try {
                Thread.sleep(1000);
                String resultGroupA = groupAService.sayHello("world"); // call remote method
                System.out.println(resultGroupA); // get result

                String resultGroupB = groupBService.sayHello("world");
                System.out.println(resultGroupB); // get result

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}

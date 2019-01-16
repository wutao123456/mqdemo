package com.dlh.dubbo.group.merge;

import com.dlh.dubbo.group.merge.api.MergeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/15 21:46
 */
public class MergeConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/merge-consumer.xml"});
        context.start();
        MergeService mergeService = (MergeService) context.getBean("mergeService");
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                List<String> result = mergeService.mergeResult();
                System.out.println("(" + i + ") " + result);
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

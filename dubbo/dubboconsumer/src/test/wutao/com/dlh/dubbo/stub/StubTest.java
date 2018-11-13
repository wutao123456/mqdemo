package com.dlh.dubbo.stub;

import com.dlh.dubbo.demo.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author wutao
 * @date 2018/11/13
 */
public class StubTest {

    private static ClassPathXmlApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("consumer.xml");
        ctx.start();
        System.out.println("consumer start");
    }

    /**
     * 测试本地存根
     */
    @Test
    public void test(){
        DemoService demoService = ctx.getBean(DemoService.class);
        List<String> result = demoService.getPermissions(2L);
        System.out.println(result);
    }
}

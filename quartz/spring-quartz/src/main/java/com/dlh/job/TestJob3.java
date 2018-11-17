package com.dlh.job;

import com.dlh.service.TestService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/17 16:25
 */
public class TestJob3 implements Job {

    @Autowired
    private TestService testService;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //具体来讲，Servlet中本来不能使用@Autowired注入bean，
        // 解决办法是在Servlet的init(ServletConfig)方法中调用SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this)，
        // 就可以直接使用@Autowired来注入Web Application Context下的一些Service等Bean了。
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        System.out.println("job3-" + System.currentTimeMillis());
        testService.test();
        try {
            //获取到了调度器就可以获取所有信息，修改任务、触发器等
            List<String> list = scheduler.getJobGroupNames();
            System.out.println(list);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

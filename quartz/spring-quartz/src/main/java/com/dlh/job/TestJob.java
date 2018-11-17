package com.dlh.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/17 15:48
 */
public class TestJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("job"+System.currentTimeMillis());
    }
}

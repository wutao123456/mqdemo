package com.dlh.wutao.job;

import org.quartz.*;

import java.util.Date;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/17 14:30
 */

/**
 * Job是有可能并发执行的，比如一个任务要执行10秒中，而调度算法是每秒中触发1次，那么就有可能多个任务被并发执行。
 * @DisallowConcurrentExecution 避免并发问题,导致数据紊乱
 * 注意，@DisallowConcurrentExecution是对JobDetail实例生效，也就是如果你定义两个JobDetail，引用同一个Job类，是可以并发执行的。
 */
@DisallowConcurrentExecution
public class HelloJob implements Job {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //JobDetail Trigger中都可以设置JobDataMap属性，这是为了在多个Trigger中使用相同的Job。
    //JobExecutionContext 将会合并JobDetail与Trigger的JobDataMap，如果其中属性名相同，后者将覆盖前者。
    //可以使用JobExecutionContext.getMergedJobDataMap()方法来获取合并后的JobDataMap。

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(String.format("Hello World! Time:%s", new Date()));
        System.out.printf(name);

        //获取jobdetail、trigger等配置信息
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();


        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobDataMap triggerDataMap = jobExecutionContext.getTrigger().getJobDataMap();
        //获取合并后的JobDataMap
        JobDataMap mergedJobDataMap= jobExecutionContext.getMergedJobDataMap();


    }
}

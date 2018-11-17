package com.dlh.wutao.main;

import com.dlh.wutao.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/17 14:32
 */
public class HelloWorldDemo {

    public static void main(String[] args) {
        try {
            // 通过schedulerFactory获取一个调度器
            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("name","wutao");

            // 创建jobDetail实例，绑定Job实现类
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("helloJob","helloGroup")
                    .setJobData(jobDataMap)
                    .storeDurably() //任务的持久性:如果一个任务不是durable，那么当没有Trigger关联它的时候，它就会被自动删除
                    .requestRecovery() //故障恢复:如果一个任务是"requests recovery"，那么当任务运行过程非正常退出时（比如进程崩溃，机器断电，但不包括抛出异常这种情况），Quartz再次启动时，会重新运行一次这个任务实例。
                    .build();


            JobDataMap triggerDataMap = new JobDataMap();
            triggerDataMap.put("name","xl");
            // 定义调度触发规则，本例中使用SimpleScheduleBuilder创建了一个5s执行一次的触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("helloTrigger","triggerGroup1")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                    .usingJobData(triggerDataMap)
                    .build();

            //把作业和调度任务注册到任务调度中
            scheduler.scheduleJob(jobDetail,trigger);

            //启动调度
            scheduler.start();
            Thread.sleep(1000*30);
            scheduler.shutdown();
            System.out.printf("调度任务结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

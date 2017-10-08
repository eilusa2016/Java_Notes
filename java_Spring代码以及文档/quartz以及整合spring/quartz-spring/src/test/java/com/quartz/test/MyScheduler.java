package com.quartz.test;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 我的调度器
 * @author Administrator
 *
 */
public class MyScheduler {
	
	public static void main(String[] args) {
		Scheduler scheduler=null;
		try {
			new MyScheduler().buildSimpleScheduler(MyJob.class);
			//scheduler=new MyScheduler().buildCronSchdulerAndStart(MyJob.class);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建调度器
	 * @param job
	 * @throws SchedulerException 
	 */
	public void buildSimpleScheduler(Class<? extends Job> job) throws SchedulerException{
		JobDetail jobDetail=JobBuilder.
								newJob(job).
								withIdentity("myJob01","group1").
								build();
//		创建一个触发器,立即执行  每个两秒钟执行 
//		也可以在一段时间内执行: .startAt(new Date()).endAt(new Date())
//		推荐使用Cron
		Trigger trigger=TriggerBuilder.newTrigger()
								.withIdentity("myTrigger", "triggerGroup1")
								.startNow()
								.withSchedule(SimpleScheduleBuilder
												.simpleSchedule()
												.withIntervalInSeconds(2)
												//重复执行
												.repeatForever()
												//重复执行几次
												//.withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)
												)
								.build();
		// 定义调度器
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        
        sched.scheduleJob(jobDetail, trigger);
        //启动 
        sched.start();
        
       // sched.shutdown(true);
       
	}
	
	
	/**
	 * 创建一个自定义时间的定时器
	 * @param job
	 * @throws SchedulerException 
	 */
	public Scheduler buildCronSchdulerAndStart(Class<? extends Job> job) throws SchedulerException{
		JobDetail jobDetail=JobBuilder.newJob(job)
				.withIdentity("myjob", "group1")
				.usingJobData("stringKey", "hello")//使用JobData
				.usingJobData("floatKey", 1.7f)
				.build();
		
		//每个两秒钟执行一次
		Trigger trigger=TriggerBuilder.newTrigger()
									  .withIdentity("myTrigger", "TriggerGroup1")
									  .usingJobData("triggerStringKey", "helloTrigger")//触发器这里也可以使用
									  .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
									  .build();
		// 获取当前时间的下一分钟 
        //Date runTime = DateBuilder.evenMinuteDate(new Date());
		// 定义触发器，在下一分钟启动
        //Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
		
		// 定义调度器
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        sched.scheduleJob(jobDetail, trigger);
        
        //启动 
        sched.start();
        return sched;
        
	}
	
	
}

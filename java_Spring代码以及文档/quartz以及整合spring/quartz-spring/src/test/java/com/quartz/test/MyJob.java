package com.quartz.test;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * 自己的定时任务 直接写逻辑就行了
 * 
 * @author Administrator
 *
 */
public class MyJob implements Job {

	/**
	 * 几点注意的地方: 1,JobExecutionContext
	 * 概念：Job能通过JobExecutionContext访问Quartz运行环境以及Job的明细数据，
	 * 当Scheduler调用Job时能将数据传递给execute()方法
	 * 
	 * 2,JobDataMap
	 * 概念：是一个JDK中Map接口实例，在任务调度时存在于JobExecutionContext中，可将参数对象传递给在运行的Job实例；而且，
	 * 它自身有方便的方法可存取基本数据类型
	 * 获取方式：从JobDataMap中直接获取、在Job实现类中添加setter方法对应JobDataMap的键值
	 * 
	 * 3,每一次执行job都会新创建一个job实例，
	 * 	执行完后交给垃圾回收。
	 * JobExecutionContext包含job执行的环境信息以及jobDetail信息
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		System.out.println("Hello:" + new Date());

		//得到JobData
		JobKey jKey=context.getJobDetail().getKey();
		System.out.println("JobKey:"+jKey.getName()+"\t"+jKey.getGroup());
		TriggerKey triggerKey=context.getTrigger().getKey();
		System.out.println("triggerKey:"+triggerKey.getName()+"\t"+triggerKey.getGroup());
		
		//1,JobDataMap
		//JobDetail的JobDataMap
		JobDataMap jobDataMap=context.getJobDetail().getJobDataMap();
//		jobDataMap.get(key)
//		jobDataMap.getFloat(key)
//		jobDataMap.getString(key)
//		...
		JobDataMap jobDataMap2=context.getTrigger().getJobDataMap();
		
		
		//上面要两次去JobDataMap  这种方式的话
		//2,JobDetail 和Trigger中相同的Key  会优先 Trigger的Key
		JobDataMap  mergedJobDataMap=context.getMergedJobDataMap();
		
		//3,这种获取方式可以在这个类中直接定义 JobDataMap中  key名字对应的类型字段
		//但是必须封装 setter方法  通过设置变量属性，使用getter（）和setter（）方法来进行传值。
	}
}

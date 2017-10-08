package com.spring.service.Tasks;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

/**
 * 自定义的任务
 * 1,Spring结合使用的时候
 * 	其实不需要实现 Job这个接口
 * @author Administrator
 *
 */
@Service("myQuartz")
public class MyQuartz {
	
	/**
	 * 不能加入参数
	 */
	public void executeTask(){
		System.out.println("hello spring quartz!");
	}

	

}

package com.spring.service.Tasks;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.spring.service.entity.AnotherBean;
/**
 * 这个类没哟注解 
 * 需要集成QuartzJobBean类
 * @author Administrator
 *
 */
public class FirstScheduledJob extends QuartzJobBean {
	//xml里面的注入的bean
	private AnotherBean anotherBean;
		
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		anotherBean.printAnotherMessage();
	}

	public AnotherBean getAnotherBean() {
		return anotherBean;
	}

	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}



}

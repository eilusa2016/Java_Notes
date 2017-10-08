package com.quartz.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.quartz.SchedulerException;

@RunWith(JUnit4.class)
public class QUartzSample {
	private MyScheduler scheduler;

	@Before 
	public void TestInit() {
		scheduler=new MyScheduler();
	}

	@Test
	public void Quartz01() {
		
		try {
			scheduler.buildSimpleScheduler(MyJob.class);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

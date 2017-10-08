package com.xjb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xjb.service.EhCacheTestService;


//public class SpringTestCase extends AbstractJUnit4SpringContextTests {
@ContextConfiguration(locations={"classpath:spring/application.xml"})
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner  
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestCase {

	@Autowired
	private EhCacheTestService ehCacheTestService;
	
	@Test
	public void getTimestampTest() throws InterruptedException{
//		System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("key2"));
//        Thread.sleep(2000);
//        System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("key2"));
//        Thread.sleep(2000);
//        System.out.println("再过2秒之后调用：" + ehCacheTestService.getTimestamp("key2"));
		
	
		
	}
}

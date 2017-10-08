package com.xjb.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xjb.service.EhCacheTestService;

public class EhCacheTestServiceTest extends SpringTestCase {
	@Autowired
	private EhCacheTestService ehCacheTestService;
	
	@Test
	public void getTimestampTest() throws InterruptedException{
		System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("key2"));
//        Thread.sleep(2000);
//        System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("keyFirst"));
//        Thread.sleep(2000);
//        System.out.println("再过2秒之后调用：" + ehCacheTestService.getTimestamp("keyFirst"));
		
	}
}

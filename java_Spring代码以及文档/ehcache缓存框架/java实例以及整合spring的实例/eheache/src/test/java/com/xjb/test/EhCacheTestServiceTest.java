package com.xjb.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xjb.service.EhCacheTestService;

public class EhCacheTestServiceTest extends SpringTestCase {
	@Autowired
	private EhCacheTestService ehCacheTestService;
	
	@Test
	public void getTimestampTest() throws InterruptedException{
		System.out.println("��һ�ε��ã�" + ehCacheTestService.getTimestamp("key2"));
//        Thread.sleep(2000);
//        System.out.println("2��֮����ã�" + ehCacheTestService.getTimestamp("keyFirst"));
//        Thread.sleep(2000);
//        System.out.println("�ٹ�2��֮����ã�" + ehCacheTestService.getTimestamp("keyFirst"));
		
	}
}

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
//ʹ�ñ�׼��JUnit @RunWithע��������JUnitʹ��Spring TestRunner  
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestCase {

	@Autowired
	private EhCacheTestService ehCacheTestService;
	
	@Test
	public void getTimestampTest() throws InterruptedException{
//		System.out.println("��һ�ε��ã�" + ehCacheTestService.getTimestamp("key2"));
//        Thread.sleep(2000);
//        System.out.println("2��֮����ã�" + ehCacheTestService.getTimestamp("key2"));
//        Thread.sleep(2000);
//        System.out.println("�ٹ�2��֮����ã�" + ehCacheTestService.getTimestamp("key2"));
		
	
		
	}
}

package org.svse.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.svse.entity.StuInfo;
import org.svse.service.StuInfoService;

public class StuInfoServiceTest {
	
	
	public static void main(String[] args) {
		
		test1();
		
	}
	
	
	public static void test1(){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		StuInfoService s =(StuInfoService) ctx.getBean("stuService");
		System.out.println(s.getClass().getName());
		StuInfo stu = new StuInfo();
		stu.setStuname("��Ԫ5");
		stu.setStuage(20);
		stu.setStubirthday(new Date());
		try {
			s.saveStuInfo(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//throw new RuntimeException("�Ҵ��ˣ���Ҳ���ٵ���");
		
	}
	
	
	

}

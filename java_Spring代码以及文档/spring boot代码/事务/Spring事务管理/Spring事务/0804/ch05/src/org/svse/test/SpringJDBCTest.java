package org.svse.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.svse.dao.StuDAO;
import org.svse.entity.StuInfo;

public class SpringJDBCTest {

	public static void main(String[] args) {

		test4();

	}

	public static void test1() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");

		StuDAO studao = (StuDAO) ctx.getBean("stuDao");

		StuInfo stu = new StuInfo();
		stu.setStuname("小包");
		stu.setStuage(16);
		stu.setStubirthday(new Date());

		studao.addStu(stu);

		System.out.println("ok");

	}

	public static void test2() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");

		StuDAO studao = (StuDAO) ctx.getBean("stuDao");

		List<StuInfo> stus = studao.findAll();
		for (StuInfo stu : stus) {
			System.out.println("stuid:" + stu.getStuid() + "----stuname:"
					+ stu.getStuname());
		}

	}

	public static void test3() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");

		StuDAO studao = (StuDAO) ctx.getBean("stuDao");

		StuInfo stu = studao.findById(2);
		System.out.println(stu.getStuname());
	}

	public static void test4() {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");

		StuDAO studao = (StuDAO) ctx.getBean("stuDao2");
		
		
		StuInfo stu = new StuInfo();
		stu.setStuname("大包");
		stu.setStuage(16);
		stu.setStubirthday(new Date());
		
		
		studao.addStu(stu);

	}

}

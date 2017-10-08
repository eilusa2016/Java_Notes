package com.jpa.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jpa.desc.DescClass;
import com.jpa.desc.DescMethod;
import com.jpa.helloworld.Customer;

@DescClass(name="测试jpa的类")
public class JPATest {
	public static void main(String[] args) {
		testHelloWorld();
	}
	
	@DescMethod(name="测试helloWorld",des="persistence.xml  这个文件名是固定的")
	private static void testHelloWorld(){
			//1. 创建 EntitymanagerFactory，这里的name必须和xml中配置的一样，而且必须配置
			String persistenceUnitName = "JPA_01";
			Map<String, Object> properites = new HashMap<String, Object>();
			properites.put("hibernate.show_sql", true);
			EntityManagerFactory entityManagerFactory = 
					//Persistence.createEntityManagerFactory(persistenceUnitName);
					Persistence.createEntityManagerFactory(persistenceUnitName, properites);
			//2. 创建 EntityManager. 类似于 Hibernate 的 SessionFactory
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			
			//3. 开启事务
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			//4. 进行持久化操作
			Customer customer = new Customer();
			customer.setAge(12);
			customer.setEmail("tom@atguigu.com");
			customer.setLastName("Tom");
			customer.setBirth(new Date());
			customer.setCreatedTime(new Date());
			
			entityManager.persist(customer);
			
			//5. 提交事务
			transaction.commit();
			
			//6. 关闭 EntityManager
			entityManager.close();
			
			//7. 关闭 EntityManagerFactory
			entityManagerFactory.close();
	}
	
}

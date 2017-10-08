package com.jpa.test.双向一对一;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jpa.desc.DescClass;
import com.jpa.desc.DescMethod;
import com.jpa.helloworld.Customer;
import com.jpa.helloworld.Department;
import com.jpa.helloworld.Manager;
import com.jpa.helloworld.Order;

public class JunitJPATest {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("JPA_01");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	}

	@After
	public void destory() {
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	// 1. 默认情况下, 若获取不维护关联关系的一方, 则也会通过左外连接获取其关联的对象.
	// 可以通过 @OneToOne 的 fetch 属性来修改加载策略. 但依然会再发送 SQL 语句来初始化其关联的对象
	// 这说明在不维护关联关系的一方, 不建议修改 fetch 属性.
	@Test
	public void testOneToOneFind2() {
		Manager mgr = entityManager.find(Manager.class, 1);
		System.out.println(mgr.getMgrName());

		System.out.println(mgr.getDept().getClass().getName());
	}

	// 1.默认情况下, 若获取维护关联关系的一方, 则会通过左外连接获取其关联的对象.
	// 但可以通过 @OntToOne 的 fetch 属性来修改加载策略.
	@Test
	public void testOneToOneFind() {
		Department dept = entityManager.find(Department.class, 1);
		System.out.println(dept.getDeptName());
		System.out.println(dept.getMgr().getClass().getName());
	}

	// 双向 1-1 的关联关系, 建议先保存不维护关联关系的一方, 即没有外键的一方, 这样不会多出 UPDATE 语句.
	@Test
	public void testOneToOnePersistence() {
		Manager mgr = new Manager();
		mgr.setMgrName("M-BB");

		Department dept = new Department();
		dept.setDeptName("D-BB");

		// 设置关联关系
		mgr.setDept(dept);
		dept.setMgr(mgr);

		// 执行保存操作
		entityManager.persist(mgr);
		entityManager.persist(dept);
	}
}

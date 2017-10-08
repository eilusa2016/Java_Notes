package com.jpa.test.二级缓存;

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
import com.jpa.helloworld.Category;
import com.jpa.helloworld.Customer;
import com.jpa.helloworld.Department;
import com.jpa.helloworld.Item;
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

	/**
	 * 二级缓存其实就是跨session
	 * 需要使用实现二级缓存的产品
	 * ehcache
	 */
	@Test
	public void testSecondLevelCache() {
		Customer customer1 = entityManager.find(Customer.class, 44);

		entityTransaction.commit();
		entityManager.close();

		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Customer customer2 = entityManager.find(Customer.class, 44);
	}

}

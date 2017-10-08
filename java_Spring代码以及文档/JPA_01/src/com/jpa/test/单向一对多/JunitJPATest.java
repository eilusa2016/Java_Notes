package com.jpa.test.单向一对多;

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

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		Customer customer = entityManager.find(Customer.class, 47);
		customer.getOrders().iterator().next().setOrderName("O-XXX-10");
	}

	// 默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除.
	// 可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
	@Test
	public void testOneToManyRemove() {
		Customer customer = entityManager.find(Customer.class, 48);
		entityManager.remove(customer);
	}

	// 默认对关联的多的一方使用懒加载的加载策略.
	// 可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	@Test
	public void testOneToManyFind() {
		Customer customer = entityManager.find(Customer.class, 48);
		System.out.println(customer.getLastName());

		System.out.println(customer.getOrders().size());
	}

	// 单向 1-n 关联关系执行保存时, 一定会多出 UPDATE 语句.
	// 因为 n 的一端在插入时不会同时插入外键列.
	@Test
	public void testOneToManyPersist() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@qq.com");
		customer.setLastName("MM");

		Order order1 = new Order();
		order1.setOrderName("O-MM-1");

		Order order2 = new Order();
		order2.setOrderName("O-MM-2");

		// 建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);

		// 执行保存操作,这里是由customer维护外键关系
		// 所以保存order的时候 会有额外的uodate语句
		entityManager.persist(customer);

		entityManager.persist(order1);
		entityManager.persist(order2);
	}

}

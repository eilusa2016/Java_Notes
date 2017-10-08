package com.jpa.test.单向多对一;

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

	/**
	 * 多对一的更新
	 */
	@Test
	public void testManyToOneUpdate(){
		Order order = entityManager.find(Order.class, 2);
		order.getCustomer().setLastName("FFF");
	}
	
	/**
	 * 不能直接删除 1 的一端, 因为有外键约束.
	 * 也就是说  不能删除主键表的那条记录
	 */
	@Test
	public void testManyToOneRemove() {
		// Order order = entityManager.find(Order.class, 1);
		// entityManager.remove(order);
		Customer customer = entityManager.find(Customer.class, 47);
		entityManager.remove(customer);
	}

	// 默认情况下（看控制台的信息）, 使用左外连接的方式来获取 n 的一端的对象和其关联的 1 的一端的对象.
	// 可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略 如：@ManyToOne(fetch =
	// FetchType.LAZY)
	@Test
	public void testManyToOneFind() {
		Order order = entityManager.find(Order.class, 1);
		System.out.println(order.getOrderName());

		System.out.println(order.getCustomer().getLastName());
	}

	/**
	 * 保存多对一时, 建议先保存 1 的一端, 后保存 n 的一端, 这样不会多出额外的 UPDATE 语句.
	 */
	@Test
	public void testManyToOnePersist() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("gg@126.com");
		customer.setLastName("GG");

		Order order1 = new Order();
		order1.setOrderName("G-GG-1");

		Order order2 = new Order();
		order2.setOrderName("G-GG-2");

		// 设置关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);

		// 最好先保存它
		entityManager.persist(customer);
		// 执行保存操作
		entityManager.persist(order1);
		entityManager.persist(order2);
		// entityManager.persist(customer);
	}
}

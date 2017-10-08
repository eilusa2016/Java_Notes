package com.jpa.test.jpsql.c_ORDER_BY和GROUP_BY;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpa.desc.DescClass;
import com.jpa.desc.DescMethod;
import com.jpa.helloworld.Customer;

@DescClass(name = "测试jpql的类", des = "")
public class JPQLOrderAndGroup {
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

	// 查询 order 数量大于 2 的那些 Customer
	@Test
	public void testGroupBy() {
		String jpql = "SELECT o.customer FROM Order o "
				+ "GROUP BY o.customer " + "HAVING count(o.id) >=?";
		List<Customer> customers = entityManager.createQuery(jpql).setParameter(1, 2l).getResultList();
		for(Customer cus:customers){
			System.out.println(cus);
		}
		
	}

	@DescMethod(des = "jpql 可以使用order by 没什么区别")
	@Test
	public void testOrderBy() {
		String jpql = "FROM Customer c WHERE c.age > ? ORDER BY c.age DESC";
		Query query = entityManager.createQuery(jpql).setHint(
				QueryHints.HINT_CACHEABLE, true);

		// 占位符的索引是从 1 开始
		query.setParameter(1, 1);
		List<Customer> customers = query.getResultList();
		System.out.println(customers.size());
	}

}

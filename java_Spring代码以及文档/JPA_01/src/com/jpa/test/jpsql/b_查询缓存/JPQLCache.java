package com.jpa.test.jpsql.b_查询缓存;

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
import com.jpa.helloworld.Customer;

@DescClass(name = "jpql  查询缓存")
public class JPQLCache {
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

	// 使用 hibernate 的查询缓存.
	//这样  第二次查询就从缓存中读出
	//需要在配置文件中配置了查询缓存：<property name="hibernate.cache.use_query_cache" value="true" />
	@Test
	public void testQueryCache() {
		String jpql = "FROM Customer c WHERE c.age > ?";
		Query query = entityManager.createQuery(jpql).setHint(
				QueryHints.HINT_CACHEABLE, true);

		// 占位符的索引是从 1 开始
		query.setParameter(1, 44);
		List<Customer> customers = query.getResultList();
		System.out.println(customers.size());

		query = entityManager.createQuery(jpql).setHint(
				org.hibernate.ejb.QueryHints.HINT_CACHEABLE, true);

		// 占位符的索引是从 1 开始
		query.setParameter(1, 44);
		customers = query.getResultList();
		System.out.println(customers.size());
	}

}

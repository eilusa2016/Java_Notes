package com.jpa.test.jpsql.e_子查询和内建函数;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpa.desc.DescClass;
import com.jpa.helloworld.Order;

@DescClass(name = "jpql  自查询")
public class JPQLSubSearch {
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
	 *  使用 jpql 内建的函数
	 * concat(String s1, String s2)：字符串合并/连接函数。 
	 * substring(String s, int start,int length)：取字串函数。 
	 * trim([leading|trailing|both,] [char c,] String s)：从字符串中去掉首/尾指定的字符或空格。 
	 * lower(String s)：将字符串转换成小写形式。
	 * upper(String s)：将字符串转换成大写形式。 
	 * length(String s)：求字符串的长度。 
	 * locate(String s1, String s2[, int start])：从第一个字符串中查找第二个字
	 */
	@Test
	public void testJpqlFunction() {
		String jpql = "SELECT lower(c.email) FROM Customer c";

		List<String> emails = entityManager.createQuery(jpql).getResultList();
		System.out.println(emails);
	}

	// 子查询
	@Test
	public void testSubQuery() {
		// 查询所有 Customer 的 lastName 为 YY 的 Order
		String jpql = "SELECT o FROM Order o "
				+ "WHERE o.customer = (SELECT c FROM Customer c WHERE c.lastName = ?)";

		Query query = entityManager.createQuery(jpql).setParameter(1, "YY");
		List<Order> orders = query.getResultList();
		System.out.println(orders.size());
	}

}

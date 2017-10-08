package com.jpa.test.jpsql.d_关联查询;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpa.desc.DescClass;
import com.jpa.helloworld.Customer;

@DescClass(name = "jpql  关联查询")
public class JPQLUnion {
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
	 * JPQL 的关联查询同 HQL 的关联查询.
	 * 如果去掉  FETCH 会出错
	 * 这里的"fetch"关键字表明Order对象读出以后立即填充到对应的Customer对象
	 *(orders集合属性)中。
	 */
	@Test
	public void testLeftOuterJoinFetch() {
		String jpql = "FROM Customer c LEFT OUTER JOIN FETCH  c.orders WHERE c.id = ?";
		Query query=entityManager.createQuery(jpql)
		.setParameter(1, 44);
		Customer customer;
		try {
			customer = (Customer) query.getSingleResult();
			if(customer!=null){
				System.out.println(customer.getLastName());
				System.out.println(customer.getOrders().size());
			}else{
				System.out.println("no  results");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//这句话的意思是不加fetch的时候  返回的是一个 List<Object[]> 没有转换
		// List<Object[]> result =
		// entityManager.createQuery(jpql).setParameter(1, 12).getResultList();
		// System.out.println(result);
	}

}

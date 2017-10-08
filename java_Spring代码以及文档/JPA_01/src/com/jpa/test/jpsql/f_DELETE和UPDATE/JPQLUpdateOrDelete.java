package com.jpa.test.jpsql.f_DELETE和UPDATE;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jpa.desc.DescClass;

@DescClass(name = "jpql update  和 delete")
public class JPQLUpdateOrDelete {
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
	public void  testJPQLDelete(){
		String jpql = "delete Customer c where c.id=? and c.lastName=? ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, 43).setParameter(2, "CC");
		int len=query.executeUpdate();
		System.out.println(len);
	}
	
	// 可以使用 JPQL 完成 UPDATE 和 DELETE 操作.
	@Test
	public void testExecuteUpdate() {
		String jpql = "UPDATE Customer c SET c.lastName = ? WHERE c.id = ?";
		Query query = entityManager.createQuery(jpql).setParameter(1, "YYY")
				.setParameter(2, 44);

		query.executeUpdate();
	}

}

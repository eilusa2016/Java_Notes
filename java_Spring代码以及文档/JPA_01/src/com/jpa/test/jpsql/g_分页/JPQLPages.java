package com.jpa.test.jpsql.g_分页;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

public class JPQLPages {
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
	 * Query接口的主要方法:
		*int executeUpdate()
		*用于执行update或delete语句。
		*List getResultList()
		*用于执行select语句并返回结果集实体列表。
		*Object getSingleResult()
		*用于执行只返回单个结果实体的select语句。
		*Query setFirstResult(int startPosition)
		*用于设置从哪个实体记录开始返回查询结果。
		*Query setMaxResults(int maxResult) 
		*用于设置返回结果实体的最大数。与setFirstResult结合使用可实现分页查询。
		*Query setFlushMode(FlushModeType flushMode) 
		*设置查询对象的Flush模式。参数可以取2个枚举值：FlushModeType.AUTO 为自动更新数据库记录，FlushMode Type.COMMIT 为直到提交事务时才更新数据库记录。
	 */
	

}

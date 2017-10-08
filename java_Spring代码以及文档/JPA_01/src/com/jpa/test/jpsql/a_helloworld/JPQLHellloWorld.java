package com.jpa.test.jpsql.a_helloworld;

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
import com.jpa.desc.DescMethod;
import com.jpa.helloworld.Customer;

@DescClass(name = "测试jpql的类，helloworld")
public class JPQLHellloWorld {

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

	// createNativeQuery 适用于本地 SQL
	//就是数据库的标准sql
	@Test
	public void testNativeQuery() {
		String sql = "SELECT age FROM jpa_cutomers WHERE id = ?";
		Query query = entityManager.createNativeQuery(sql).setParameter(1, 44);

		Object result = query.getSingleResult();
		System.out.println(result);
	}

	// createNamedQuery 适用于在实体类前使用 @NamedQuery 标记的查询语句
	@Test
	public void testNamedQuery() {
		Query query = entityManager.createNamedQuery("testNamedQuery")
				.setParameter(1, 40);
		Customer customer = (Customer) query.getSingleResult();

		System.out.println(customer);
	}

	// 默认情况下, 若只查询部分属性, 则将返回 Object[] 类型的结果. 或者 Object[] 类型的 List.
	// 也可以在实体类中创建对应的构造器, 然后再 JPQL 语句中利用对应的构造器返回实体类的对象.
	@Test
	public void testPartlyProperties() {
		// 这里需要 Customer 中有两个参数的构造
		String jpql = "SELECT new Customer(c.lastName, c.age) FROM Customer c WHERE c.id > ?";
		List result = entityManager.createQuery(jpql).setParameter(1, 40)
				.getResultList();

		System.out.println(result);
	}

	@DescMethod(name = "jpql   hello", des = "和hql很像")
	@Test
	public void testHelloJPQL() {
		String jpql = "FROM Customer c WHERE c.age > ?";
		Query query = entityManager.createQuery(jpql);

		// 占位符的索引是从 1 开始
		query.setParameter(1, 44);
		List<Customer> customers = query.getResultList();
		System.out.println(customers.size());
	}

}

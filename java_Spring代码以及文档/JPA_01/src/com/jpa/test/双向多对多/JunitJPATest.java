package com.jpa.test.双向多对多;

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

	// 对于关联的集合对象, 默认使用懒加载的策略.
	// 使用维护关联关系的一方获取, 还是使用不维护关联关系的一方获取, SQL 语句相同.
	@Test
	public void testManyToManyFind() {
		// Item item = entityManager.find(Item.class, 5);
		// System.out.println(item.getItemName());
		//
		// System.out.println(item.getCategories().size());

		Category category = entityManager.find(Category.class, 2);
		System.out.println(category.getCategoryName());
		System.out.println(category.getItems().size());
	}

	// 多对所的保存
	@Test
	public void testManyToManyPersist() {
		Item i1 = new Item();
		i1.setItemName("i-1");

		Item i2 = new Item();
		i2.setItemName("i-2");

		Category c1 = new Category();
		c1.setCategoryName("C-1");

		Category c2 = new Category();
		c2.setCategoryName("C-2");

		// 设置关联关系
		i1.getCategories().add(c1);
		i1.getCategories().add(c2);

		i2.getCategories().add(c1);
		i2.getCategories().add(c2);

		c1.getItems().add(i1);
		c1.getItems().add(i2);

		c2.getItems().add(i1);
		c2.getItems().add(i2);

		// 执行保存
		entityManager.persist(i1);
		entityManager.persist(i2);
		entityManager.persist(c1);
		entityManager.persist(c2);
	}

}

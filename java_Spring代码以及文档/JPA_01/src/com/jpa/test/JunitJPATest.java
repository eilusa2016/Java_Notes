package com.jpa.test;

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

	/**
	 * 同 hibernate 中 Session 的 refresh 方法.
	 */
	@Test
	public void testRefresh() {
		Customer customer = entityManager.find(Customer.class, 44);
		customer = entityManager.find(Customer.class, 45);
		entityManager.refresh(customer);
	}

	/**
	 * 同 hibernate 中 Session 的 flush 方法.
	 */
	@Test
	public void testFlush() {
		Customer customer = entityManager.find(Customer.class, 44);
		System.out.println(customer);
		customer.setLastName("AA");

		entityManager.flush();
	}

	// 若传入的是一个游离对象, 即传入的对象有 OID.
	// 1. 若在 EntityManager 缓存中有对应的对象
	// 2. JPA 会把游离对象的属性复制到查询到EntityManager 缓存中的对象中.
	// 3. EntityManager 缓存中的对象执行 UPDATE.
	@Test
	public void testMerge4() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("dd@163.com");
		customer.setLastName("DD");
		customer.setId(40);
		Customer customer2 = entityManager.find(Customer.class, 40);

		entityManager.merge(customer);

		System.out.println(customer == customer2); // false
	}

	// 若传入的是一个游离对象, 即传入的对象有 OID.
	// 1. 若在 EntityManager 缓存中没有该对象
	// 2. 若在数据库中也有对应的记录
	// 3. JPA 会查询对应的记录, 然后返回该记录对一个的对象, 再然后会把游离对象的属性复制到查询到的对象中.
	// 4. 对查询到的对象执行 save or update 操作.
	@Test
	public void testMerge3() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("ee@163.com");
		customer.setLastName("EE2");

		customer.setId(20);

		Customer customer2 = entityManager.merge(customer);

		System.out.println(customer == customer2); // false
	}

	// 若传入的是一个游离对象, 即传入的对象有 OID.
	// 1. 若在 EntityManager 缓存中没有该对象
	// 2. 若在数据库中也没有对应的记录
	// 3. JPA 会创建一个新的对象, 然后把当前游离对象的属性复制到新创建的对象中
	// 4. 对新创建的对象执行 insert or update 操作.
	@Test
	public void testMerge2() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("dd@163.com");
		customer.setLastName("DD2");

		customer.setId(40);

		Customer customer2 = entityManager.merge(customer);

		System.out.println("customer#id:" + customer.getId());
		System.out.println("customer2#id:" + customer2.getId());
	}

	/**
	 * 总的来说: 类似于 hibernate Session 的 saveOrUpdate 方法.
	 */
	// 1. 若传入的是一个临时对象
	// 会创建一个新的对象, 把临时对象的属性复制到新的对象中, 然后对新的对象执行持久化操作. 所以
	// 新的对象中有 id, 但以前的临时对象中没有 id.
	@Test
	public void testMerge1() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("cc@163.com");
		customer.setLastName("CC");
		Customer customer2 = entityManager.merge(customer);

		System.out.println("customer#id:" + customer.getId());
		System.out.println("customer2#id:" + customer2.getId());
	}

	// 类似于 hibernate 中 Session 的 delete 方法. 把对象对应的记录从数据库中移除
	// 但注意: 该方法只能移除 持久化 对象. 而 hibernate 的 delete 方法实际上还可以移除 游离对象.
	@Test
	@DescMethod(des = "不能移除游离的对象，hibernate不是  会全删除")
	public void testRemove() {
		Customer customer = new Customer();
		customer.setId(2);
		// Customer customer = entityManager.find(Customer.class, 20);
		entityManager.remove(customer);
	}

	// 类似于 hibernate 的 save 方法. 使对象由临时状态变为持久化状态.
	// 和 hibernate 的 save 方法的不同之处: 若对象有 id, 则不能执行 insert 操作, 而会抛出异常.
	@Test
	public void testPersistence() {
		Customer customer = new Customer();
		customer.setAge(15);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("bb@163.com");
		customer.setLastName("BB");
		// customer.setId(100);

		entityManager.persist(customer);
		// customer.setLastName("BB2");//hibernate中这个修改会发送update
		System.out.println(customer.getId());
	}

	@Test
	@DescMethod(name = "类似于 hibernate 中 Session 的 load 方法", des = "使用对象属性的时候  才去select")
	public void testGetReference() {
		Customer customer = entityManager.getReference(Customer.class, 20);
		System.out.println(customer.getClass().getName());
		System.out.println("-------------------------------------");
		// 它的底层用的是hibernate 这里提交会出现懒加载异常
		// transaction.commit();
		// entityManager.close();
		System.out.println(customer);
	}

	// 类似于 hibernate 中 Session 的 get 方法.
	@Test
	public void testFind() {
		Customer customer = entityManager.find(Customer.class, 20);
		System.out.println("-------------------------------------");
		System.out.println(customer);
	}
}

package com.junit.springdata.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.springdata.Person;
import com.springdata.PersonRepository;
import com.springdata.PersonService;
import com.springdata.commonrepositorymethod.AddressRepository;

public class SpringDataTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	private PersonService personService;
	private PersonRepository personRepsotory = null;
	private ApplicationContext ctx;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepsotory = ctx.getBean(PersonRepository.class);
		personService = ctx.getBean(PersonService.class);
	}

	@Test
	public void testCommonCustomRepositoryMethod() {
		ApplicationContext ctx2 = new ClassPathXmlApplicationContext(
				"classpath:com/springdata/commonrepositorymethod/applicationContext2.xml");
		AddressRepository addressRepository = ctx2
				.getBean(AddressRepository.class);
		addressRepository.method();
	}

	@Test
	public void testCustomRepositoryMethod() {
		personRepsotory.test();
	}

	/**
	 * 目标: 实现带查询条件的分页. id > 5 的条件
	 * 
	 * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec,
	 * Pageable pageable); Specification: 封装了 JPA Criteria 查询的查询条件 Pageable:
	 * 封装了请求分页的信息: 例如 pageNo, pageSize, Sort
	 */
	@Test
	public void testJpaSpecificationExecutor() {
		int pageNo = 3 - 1;// 当前页
		int pageSize = 5;// 每页 5条记录
		PageRequest pageable = new PageRequest(pageNo, pageSize);

		// 通常使用 Specification 的匿名内部类
		Specification<Person> specification = new Specification<Person>() {
			/**
			 * @param *root: 代表查询的实体类.
			 * @param query
			 *            : 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 *            来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
			 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到
			 *        Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件.
			 */
			@Override
			public Predicate toPredicate(Root<Person> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("id");
				Predicate predicate = cb.gt(path, 5);
				// //多条件查询
				// List<Predicate> list = new ArrayList<Predicate>();
				// 。。。。
				// Predicate[] p = new Predicate[list.size()];
				// return cb.and(list.toArray(p));
				return predicate;
			}
		};

		Page<Person> page = personRepsotory.findAll(specification, pageable);

		System.out.println("总记录数: " + page.getTotalElements());
		System.out.println("当前第几页: " + (page.getNumber() + 1));
		System.out.println("总页数: " + page.getTotalPages());
		System.out.println("当前页面的 List: " + page.getContent());
		System.out.println("当前页面的记录数: " + page.getNumberOfElements());
	}

	// saveAndFlush
	@Test
	public void testJpaRepository() {
		Person person = new Person();
		person.setBirth(new Date());
		person.setAge(22);
		person.setEmail("xy@atguigu.com");
		person.setLastName("xyz");
		person.setId(28);
		Person person2 = personRepsotory.saveAndFlush(person);

		System.out.println(person == person2);
	}

	/**
	 * 分页 以及 排序 但是这个通用的分页不可以实现查询条件
	 */
	@Test
	public void testPagingAndSortingRespository() {
		// pageNo 从 0 开始.
		int pageNo = 2 - 1;// 当前第几页
		int pageSize = 5;// 每页显示几条
		// Pageable 接口通常使用的其 PageRequest 实现类. 其中封装了需要分页的信息
		// 排序相关的. Sort 封装了排序的信息
		// Order 是具体针对于某一个属性进行升序还是降序.
		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "email");
		Sort sort = new Sort(order1, order2);

		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Person> page = personRepsotory.findAll(pageable);

		System.out.println("总记录数: " + page.getTotalElements());
		System.out.println("当前第几页: " + (page.getNumber() + 1));
		System.out.println("总页数: " + page.getTotalPages());
		System.out.println("当前页面的 List: " + page.getContent());
		System.out.println("当前页面的记录数: " + page.getNumberOfElements());
	}

	// 批量的插入
	@Test
	public void testCrudReposiory() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 'a'; i <= 'z'; i++) {
			Person person = new Person();
			person.setAge(20);
			person.setAddressId(i + 1);
			person.setBirth(new Date());
			person.setEmail((char) i + "" + (char) i + "@atguigu.com");
			person.setLastName((char) i + "" + (char) i);

			persons.add(person);
		}

		personService.savePersons(persons);
	}

	// spring data 做更新的时候 需要添加事物
	@Test
	public void testModifying() {
		personService.updatePersonEmail("mmmm@atguigu.com", 1);
	}

	// 标准sql的查询
	@Test
	public void testNativeQuery() {
		long count = personRepsotory.getTotalCount();
		System.out.println(count);
	}

	@Test
	public void testQueryAnnotationLikeParam() {
		// List<Person> persons =
		// personRepsotory.testQueryAnnotationLikeParam("%b%", "%xx%");
		// System.out.println(persons.size());

		// List<Person> persons =
		// personRepsotory.testQueryAnnotationLikeParam("b", "xx");
		// System.out.println(persons.size());

		List<Person> persons = personRepsotory.testQueryAnnotationLikeParam2(
				"xx", "b");
		System.out.println(persons.size());
	}

	// @query 注解传递参数
	// 根据命名参数的方式传递 不需要区分位置了
	@Test
	public void testQueryAnnotationParams2() {
		List<Person> persons = personRepsotory.testQueryAnnotationParams2(
				"xx@atguigu.com", "bb");
		System.out.println(persons);
	}

	// @query 注解传递参数
	@Test
	public void testQueryAnnotationParams1() {
		List<Person> persons = personRepsotory.testQueryAnnotationParams1("bb",
				"xx@guigu.com");
		System.out.println(persons);
	}

	// @query 注解的测试
	@Test
	public void testQueryAnnotation() {
		Person person = personRepsotory.getMaxIdPerson();
		System.out.println(person);
	}

	// 使用关键字进行 :级连查询
	@Test
	public void testKeyWords2() {
		List<Person> persons = personRepsotory.getByAddress_IdGreaterThan(0);
		System.out.println(persons);
	}

	// 使用关键字查询
	@Test
	public void testKeyWords() {
		List<Person> persons = personRepsotory
				.getByLastNameStartingWithAndIdLessThan("X", 10);
		System.out.println(persons);

		persons = personRepsotory.getByLastNameEndingWithAndIdLessThan("X", 10);
		System.out.println(persons);

		persons = personRepsotory.getByEmailInAndBirthLessThan(Arrays.asList(
				"AA@atguigu.com", "FF@atguigu.com", "SS@atguigu.com"),
				new Date());
		System.out.println(persons.size());
	}

	/**
	 * spring data hellowprld 测试
	 */
	@Test
	public void testSpringDataHelloWorld() {
		PersonRepository personRepository = (PersonRepository) ctx
				.getBean("personRepository");
		// PersonRepository
		// personRepository=ctx.getBean(PersonRepository.class);
		Person person = personRepository.getByLastName("bb");
		// Person person=personRepository.getLastName("bb");
		System.out.println(person);
	}

	@Test
	public void TestDataSource() {
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		System.out.println(dataSource);
	}
}

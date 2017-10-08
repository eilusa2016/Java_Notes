package com.jpa.helloworld;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.jpa.desc.DescClass;

//使用 @NamedQuery
@NamedQuery(name="testNamedQuery", query="FROM Customer c WHERE c.id = ?")
@Cacheable(true)
@DescClass(des = "消费者")
@Table(name = "JPA_CUTOMERS")
@Entity
public class Customer {
	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", age=" + age + ", createdTime=" + createdTime
				+ ", birth=" + birth + "]";
	}

	private Integer id;
	private String lastName;
	private String email;
	private int age;

	private Date createdTime;
	private Date birth;

	private Set<Order> orders = new HashSet<Order>();

	// 映射单向 1-n 的关联关系
	// 使用 @OneToMany 来映射 1-n 的关联关系
	// 使用 @JoinColumn 来映射外键列的名称
	// 可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	// 可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
	// 注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用
	// @JoinColumn 属性了.
	//mappedBy="customer"  这个意思是又 n 的一方来维护关系  customer  是n的一方的属性
//	@JoinColumn(name = "CUSTOMER_ID")
//	@OneToMany //默认是lazy懒加载
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE},mappedBy="customer")
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String lastName, int age) {
		super();
		this.lastName = lastName;
		this.age = age;
	}

	// @Column(name="ID") //列名和字段名一样就不用写了
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @TableGenerator(name="ID_GENERATOR",//主键生成器的名字
	// // initialValue=1,//主键的初始值
	// table="JPA_ID_GENERATORS",//主键生成的表
	// pkColumnName="PK_NAME",//主键列
	// pkColumnValue="CUSTOMER_ID",//主键的名称
	// valueColumnName="PK_VALUE",//主键的值
	// allocationSize=20//增长的速度
	// )
	// @GeneratedValue(strategy=GenerationType.TABLE,
	// generator="ID_GENERATOR")//主键生成器的名称
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	// 时间的注解
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	// 工具方法. 不需要映射为数据表的一列.
	// 这个时候需要用到这个注解
	@Transient
	public String getInfo() {
		return "lastName: " + lastName + ", email: " + email;
	}
}

package com.jpa.helloworld;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//@NamedQuery(name="testNamedQuery", query="FROM Customer c WHERE c.id = ?")
//@Cacheable(true)
@Table(name = "JPA_CUTOMERS")
@Entity
public class Customer {
	private Integer id;
	private String lastName;
	private String email;
	private int age;

	private Date createdTime;
	private Date birth;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String lastName, int age) {
		super();
		this.lastName = lastName;
		this.age = age;
	}

//	@Column(name="ID") //列名和字段名一样就不用写了
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="LAST_NAME")
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
}

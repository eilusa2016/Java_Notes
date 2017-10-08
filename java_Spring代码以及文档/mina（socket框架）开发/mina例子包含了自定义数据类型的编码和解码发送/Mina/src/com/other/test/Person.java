package com.other.test;

import java.util.Date;

public class Person {
	private int age;
	private String name;
	private Date birth;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + ", birth=" + birth
				+ "]";
	}
	
}

package com.jpa.spring.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.spring.dao.PersonDao;
import com.jpa.spring.entities.Person;

@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;
	
	public void SavePerson(Person p1,Person p2) throws RuntimeException{
		personDao.save(p1);
		try {
			int i=10/0;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		personDao.save(p2);
	}
}

package com.view.jpa.spring.controller;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jpa.spring.entities.Person;
import com.jpa.spring.service.PersonService;

@Controller
public class JPATestController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping("/person")
	public String index(Model model){
		System.out.println("jin  ru");
		Person p1 = new Person();
		p1.setAge(11);
		p1.setEmail("aa@163.com");
		p1.setLastName("AA");
		
		Person p2 = new Person();
		p2.setAge(12);
		p2.setEmail("bb@163.com");
		p2.setLastName("BB");
		System.out.println(personService.getClass().getName());
		try {
			personService.SavePerson(p1, p2);
			model.addAttribute("msg", "生成成功");
		} catch (RuntimeException e) {
			model.addAttribute("msg", e.getMessage());
		}
		return "index";
	}
}

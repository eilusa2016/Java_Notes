package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.entity.Person;

@Controller
@RequestMapping("/indexCtr")
public class IndexController {
	

	private Person person;
	
	
	@RequestMapping(value="/init",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Person IndexInit(){
		person=new Person();
		person.setPassWord("123");
		person.setUserName("徐嘉滨");
		System.out.println(person.toString());
		System.out.println("hello");
		return person;
		
	}
}

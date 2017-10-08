package com.spring.struts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionSupport;
@Scope(value="prototype")
@Service("indexAction")
public class IndexAction extends ActionSupport {
	
	@Autowired
	private UserManageruserManager userManageruserManager;
	public String Login(){
		System.out.println("¿ªÊ¼µÇÂ½...");
		userManageruserManager.Login("system", "123456");
		
		return SUCCESS;
	}
}

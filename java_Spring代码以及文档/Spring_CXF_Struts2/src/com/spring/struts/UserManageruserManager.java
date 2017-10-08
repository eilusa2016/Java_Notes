package com.spring.struts;

import org.springframework.stereotype.Service;

@Service("userManageruserManager")
public class UserManageruserManager {
	
	public void Login(String username, String password) {  
		System.out.print(this.getClass()+",username="+username);  
	}  
}

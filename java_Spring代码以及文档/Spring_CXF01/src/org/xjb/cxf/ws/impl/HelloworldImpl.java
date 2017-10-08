package org.xjb.cxf.ws.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.stereotype.Service;
import org.xjb.cxf.ws.HelloWorld;
import org.xjb.cxf.ws.domain.Cat;
import org.xjb.cxf.ws.domain.User;
import org.xjb.cxf.ws.service.IUserService;
/**
 * service接口的实现类
 * <p>Title:HelloworldImpl</p>
 * <p>Description: </p>
 * <p>Company: </p> 
 * @author Guardians
 * @date  2016年9月27日
 */
@WebService(endpointInterface="org.xjb.cxf.ws.HelloWorld",
			serviceName="HelloWorldService")
public class HelloworldImpl implements HelloWorld {
	
	private IUserService userService;
	
	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		return "hello:你好："+name+";现在的时间是："+new Date();
	}

	@Override
	public List<Cat> getCatByUser(User user) {
		/**
		 * 在实际项目中，webservice不会实现逻辑业务
		 * 而是调用逻辑组件来暴露webservice
		 */
		//IUserService userService=new UserSerrvice();
		return userService.getCatByUser(user);
	}

	@Override
	public Map<String, Cat> getAllCats() {
		//IUserService userService=new UserSerrvice();
		Map<String, Cat> map=new HashMap<String, Cat>();
		Map<User, List<Cat>> maplist=userService.getAllCats();
		for(List<Cat> c:maplist.values()){
			for(Cat cat:c){
				map.put(cat.getName(), cat);
			} 
		}
		return map;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}

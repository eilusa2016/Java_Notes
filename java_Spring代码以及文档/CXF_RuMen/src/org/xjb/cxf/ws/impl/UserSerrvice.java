package org.xjb.cxf.ws.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xjb.cxf.ws.domain.Cat;
import org.xjb.cxf.ws.domain.User;
import org.xjb.cxf.ws.service.IUserService;

public class UserSerrvice implements IUserService {
	
	private static Map<User, List<Cat>> map=new HashMap<User, List<Cat>>();
	static 
	{
		List<Cat> list=new ArrayList<Cat>();
		list.add(new Cat(1, "cat1", "red"));
		list.add(new Cat(2, "cat2", "red"));
		list.add(new Cat(3, "cat3", "red"));
		list.add(new Cat(4, "cat4", "red"));
		list.add(new Cat(5, "cat5", "red"));
		User user=new User(1, "跃马","12345","shanghai");
		map.put(user, list);
	}
	@Override
	public List<Cat> getCatByUser(User user) {
		return map.get(user);
	}
	@Override
	public Map<User, List<Cat>> getAllCats() {
		// TODO Auto-generated method stub
		return map;
	}
	
}

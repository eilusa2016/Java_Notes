package org.xjb.cxf.ws.service;

import java.util.List;
import java.util.Map;

import org.xjb.cxf.ws.domain.Cat;
import org.xjb.cxf.ws.domain.User;

public interface IUserService {
	public List<Cat> getCatByUser(User user);

	public Map<User, List<Cat>> getAllCats();
}

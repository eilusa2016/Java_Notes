package zxn.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface RegeditService {
	/**
	 * ע�᷽��
	 * @param username
	 * @param password
	 * @return
	 */
	public String regedit(@WebParam(name = "username")String username, @WebParam(name="password")String password);
}
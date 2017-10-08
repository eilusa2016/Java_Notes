package zxn.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface RegeditService {
	/**
	 * ×¢²á·½·¨
	 * @param username
	 * @param password
	 * @return
	 */
	public String regedit(@WebParam(name = "username")String username, @WebParam(name="password")String password);
}
package zxn.ws.service;

import javax.jws.WebService;

@WebService(endpointInterface="zxn.ws.service.RegeditService",serviceName="Regedit", targetNamespace="http://service.ws.zxn/")
public class RegeditServiceImpl implements RegeditService {
	/**
	 * 注册方法
	 * @param username
	 * @param password
	 * @return
	 */
	public String regedit(String username, String password) {
		return username+"，你已成功注册！";
	}
}
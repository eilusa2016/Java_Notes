package zxn.ws.service;

import javax.jws.WebService;

@WebService(endpointInterface="zxn.ws.service.RegeditService",serviceName="Regedit", targetNamespace="http://service.ws.zxn/")
public class RegeditServiceImpl implements RegeditService {
	/**
	 * ע�᷽��
	 * @param username
	 * @param password
	 * @return
	 */
	public String regedit(String username, String password) {
		return username+"�����ѳɹ�ע�ᣡ";
	}
}
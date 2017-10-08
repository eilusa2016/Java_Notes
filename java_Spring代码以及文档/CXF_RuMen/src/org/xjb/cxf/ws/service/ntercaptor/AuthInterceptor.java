package org.xjb.cxf.ws.service.ntercaptor;


import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * 自定义的拦截器
 * 检查用户名和密码是否正确
 * @date  2016年9月28日
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	public AuthInterceptor() {
		//这个静态的参数表示在调用之前拦截soap消息
		super(Phase.PRE_INVOKE);
	}
	/**
	 * 需要自己解析
	 */
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		// TODO Auto-generated method stub
		System.out.println("消息--:"+message);
		List<Header> headers=message.getHeaders();
		if(headers==null||headers.size()<=0){
			throw new Fault(new IllegalArgumentException("却少头部，无法正确调用"));
		}
		Header firstHeader=headers.get(0);
		Element element=(Element)firstHeader.getObject();
		NodeList userid=element.getElementsByTagName("userid");
		NodeList passwd=element.getElementsByTagName("passwd");
		if(userid.getLength()!=1){
			throw new Fault(new IllegalArgumentException("用户名的格式不对"));
		}if(passwd.getLength()!=1){
			throw new Fault(new IllegalArgumentException("密码的格式不对"));
		}
		String uid=userid.item(0).getTextContent();
		String psw=passwd.item(0).getTextContent();
		//到数据库中检查
		if(!uid.equals("xjb")||!psw.equals("123456")){
			throw new Fault(new IllegalArgumentException("用户名或者密码不对，请检查用户名和密码!"));
		}
	}

	
	

}

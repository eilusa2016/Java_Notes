package lee;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.velocity.runtime.directive.Parse;
import org.apache.ws.commons.schema.utils.DOMUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * 添加用户名和密码的拦截器
 */
public class AddHeaderInterceptor  extends AbstractPhaseInterceptor<SoapMessage>{

	private String userid="";//用户名
	private String psw="";//密码
	public AddHeaderInterceptor(String i, String p) {
		super(Phase.PREPARE_SEND);
		this.userid=i;
		this.psw=p;
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		List<Header> headers=message.getHeaders();
		Document doc=DOMUtils.createDocument();
		Element auHeader=doc.createElement("authHeader");
		//此处创建的元素名称的要求应该按照服务器上的要求
		Element uid=doc.createElement("userid");
		uid.setTextContent(userid);
		Element passswd=doc.createElement("passwd");
		passswd.setTextContent(psw);
		auHeader.appendChild(uid);
		auHeader.appendChild(passswd);
		/**
		 * 上面的代码形成了
		 * <authHeader>
		 * 	<userid>xjb</userid>
		 *  <passwd>123456</passwd>
		 * </authHeader>
		 */
		headers.add(new Header(new QName("xjbjava"), auHeader));
	}

	
}

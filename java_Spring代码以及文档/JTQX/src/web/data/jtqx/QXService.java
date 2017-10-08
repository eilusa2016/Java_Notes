package web.data.jtqx;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.tools.BeanHelp;

import web.data.dao.DataDao;

@WebService(targetNamespace="http://web.data.qx",
name="QXService",//portType
serviceName="JTQX"//service
)
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)
@Service
public class QXService extends Thread {
		
	@WebMethod
	public  String gitStationEleInfo(){
		String result="";
		DataDao  dataDao=BeanHelp.getBean("dataDao");
		result=dataDao.getChangZhanS();
		return result;
	}
}

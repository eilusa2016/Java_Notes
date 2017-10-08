package lee;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.xjb.cxf.ws.Cat;
import org.xjb.cxf.ws.Entry;
import org.xjb.cxf.ws.HelloWorld;
import org.xjb.cxf.ws.StringCat;
import org.xjb.cxf.ws.User;
import org.xjb.cxf.ws.impl.HelloWorldService;

public class ClinetMain {
	public static void main(String[] args) {
		/**
		 * 把它一个工厂
		 * 这个类继承了service
		 */
		HelloWorldService factory=new HelloWorldService();
		//使用代理，返回远程的web代理
				HelloWorld hw=factory.getHelloworldImplPort();
//		//客户端添加拦截器
		Client c=ClientProxy.getClient(hw);
		c.getOutInterceptors().add(new AddHeaderInterceptor("xjb","123456"));
		c.getOutInterceptors().add(new LoggingOutInterceptor());
		
		
		System.out.println(hw.sayHi("fuck you--"));
		
		User user=new User();
		user.setId(1);
		user.setName("跃马");
		user.setPass("12345");
		user.setAddress("shanghai");
		List<Cat> list=hw.getCatByUser(user);
		for(Cat cat:list){
			//System.out.println(cat.getId()+"\t"+cat.getName());
		}
		
//		StringCat sc=hw.getAllCats();
//		for(Entry en:sc.getEntries()){
//			System.out.println(en.getKey()+"\t"+en.getValue());
//		}
		
	}
}

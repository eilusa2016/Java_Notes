package org.xjb.cxf.ws.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.xjb.cxf.ws.HelloWorld;
import org.xjb.cxf.ws.impl.HelloworldImpl;
import org.xjb.cxf.ws.service.ntercaptor.AuthInterceptor;


/**
 * 启动webservice
 * 
 * @author Guardians
 * @date 2016年9月27日
 */
public class ServiceMain {
	
//	public static void main(String[] args) throws FileNotFoundException {
//		
//		/**
//		 * //实例化ServerFactoryBean
//			JaxWsServerFactoryBean soapFactoryBean = new
//				JaxWsServerFactoryBean();
//			//提供服务类的类型（接口类型）
//			soapFactoryBean.setServiceClass(HelloWorld.class);
//			// 注意这里是实现类不是接口 
//			soapFactoryBean.setServiceClass(HelloworldImpl.class);
//			//发布服务的地址
//			soapFactoryBean.setAddress("http://192.168.1.101:7070/hw");
//	//		soapFactoryBean.getInInterceptors().add(e)
//	//		soapFactoryBean.getOutInterceptors().add(e)
//			//发布服务
//			soapFactoryBean.create();
//		 */
//		
//		/**
//		 * 其实这种是3以下的写法
//		 */
//		HelloWorld hw=new HelloworldImpl();
//		//调用 Endpoint.publish 暴露发布接口
//		EndpointImpl endpointImpl= (EndpointImpl) Endpoint.publish("http://192.168.1.101:7070/hw", hw);
////		//添加in拦截器new PrintWriter(new File("/Users/Guardians/Desktop/inlog.txt"))
////		endpointImpl.getInInterceptors().add(new LoggingInInterceptor());
////		//添加out拦截器new PrintWriter(new File("/Users/Guardians/Desktop/outlog.txt"))
////		endpointImpl.getOutInterceptors().add(new LoggingOutInterceptor());
//		/**
//		 * 自定一个权限的拦截器
//		 * AuthInterceptor
//		 * 检查用户名和  密码是否正确
//		 */
//		endpointImpl.getInInterceptors().add(new AuthInterceptor());
//		
//		
//		System.out.println("system starting.....");
//	}
}

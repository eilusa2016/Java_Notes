package com.spring.tools;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BeanFactory {
	private static WebApplicationContext webApplicationContext=ContextLoader.getCurrentWebApplicationContext();
	private static ServletContext servletContext;
	static{
		servletContext=webApplicationContext.getServletContext();
	}
	
	public static   org.springframework.beans.factory.BeanFactory getFactory(){
		org.springframework.beans.factory.BeanFactory beanFactory=WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return beanFactory;
		
	}
	
}

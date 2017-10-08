package com.websoctServlet;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

/**
 * 配置Websocket
 * (这个是servlet3 的规范  注解)
 * 扫描和包装
 * @author Administrator
 *
 */
public class WebSocketConfig implements ServerApplicationConfig {

	/**
	 * 注解方式的启动
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		System.out.println("正在扫描所有的webSocket服务！！！");
		 for (Class<?> clazz : scanned) {
			 //完整的路径
			 System.out.println(clazz.getName());
			 //包名
	         //System.out.println(clazz.getPackage().getName());
	        }
		return scanned;
	}
	
	/**
	 * 接口方式的启动
	 * 这里使用注解  这个方法不关注
	 */
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {
		// TODO Auto-generated method stub
		  Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();

//	        if (scanned.contains(EchoEndpoint.class)) {
//	            result.add(ServerEndpointConfig.Builder.create(
//	                    EchoEndpoint.class,
//	                    "/websocket/echoProgrammatic").build());
//	        }
//
//	        if (scanned.contains(DrawboardEndpoint.class)) {
//	            result.add(ServerEndpointConfig.Builder.create(
//	                    DrawboardEndpoint.class,
//	                    "/websocket/drawboard").build());
//	        }

	        return result;
	}
	
}

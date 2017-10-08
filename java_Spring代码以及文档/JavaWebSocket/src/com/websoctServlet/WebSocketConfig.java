package com.websoctServlet;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

/**
 * ����Websocket
 * (�����servlet3 �Ĺ淶  ע��)
 * ɨ��Ͱ�װ
 * @author Administrator
 *
 */
public class WebSocketConfig implements ServerApplicationConfig {

	/**
	 * ע�ⷽʽ������
	 */
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		System.out.println("����ɨ�����е�webSocket���񣡣���");
		 for (Class<?> clazz : scanned) {
			 //������·��
			 System.out.println(clazz.getName());
			 //����
	         //System.out.println(clazz.getPackage().getName());
	        }
		return scanned;
	}
	
	/**
	 * �ӿڷ�ʽ������
	 * ����ʹ��ע��  �����������ע
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

package com.tgb;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 简单的接受  
 * 很粗糙
 * @date  2016年11月20日
 */
public class TestReceve {
	private static JmsTemplate jt;

	public static void main(String[] args) throws JMSException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"ActiveMQ.xml");
		jt = (JmsTemplate) ctx.getBean("jmsTemplate");
		String  txt=(String)jt.receiveAndConvert();
//		jt.receive()
		System.out.println(txt);

	}
}

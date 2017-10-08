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
 * 简单的发送  
 * 很粗糙
 * @author Guardians
 * @date  2016年11月20日
 */
public class TestAMQ {
	
	private static JmsTemplate  jt;
	
	
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("ActiveMQ.xml");
		jt=(JmsTemplate)ctx.getBean("jmsTemplate");
		jt.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session s) throws JMSException {
				TextMessage message=(TextMessage)s.createTextMessage("spring activemq");
				System.out.println(message.getText());
				return message;
			}
		});
		
	}
}

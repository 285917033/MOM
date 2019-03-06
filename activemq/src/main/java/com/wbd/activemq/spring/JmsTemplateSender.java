package com.wbd.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class JmsTemplateSender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public static void main(String[] args) {
		
	ApplicationContext ac=	new ClassPathXmlApplicationContext("applicationContext.xml");
	
	JmsTemplateSender jts = (JmsTemplateSender) ac.getBean("jmsTemplateSender");
	//∑¢ÀÕ–≈œ¢
	jts.jmsTemplate.send(new MessageCreator() {
		
		public Message createMessage(Session s) throws JMSException {
			
			TextMessage tm = s.createTextMessage("messagelistener---");
			
			return tm;
		}
	});

	}

}

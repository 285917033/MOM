package com.wbd.activemq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsTemplateReciver {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public static void main(String[] args) {
		
	ApplicationContext ac=	new ClassPathXmlApplicationContext("applicationContext.xml");
	
	JmsTemplateReciver jts = (JmsTemplateReciver) ac.getBean("jmsTemplateReciver");
	//接收信息
	String msg = (String) jts.jmsTemplate.receiveAndConvert();
    System.out.println("收到消息"+msg);
	}

}

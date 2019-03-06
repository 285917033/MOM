package com.wbd.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener{

	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		try {
			System.out.println("监听器收到消息"+tm.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

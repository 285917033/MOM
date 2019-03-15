package com.wbd.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 接收着
 * 
 * @author jwh
 *
 */
public class ConsumerReciver {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		Connection conn = cf.createConnection();
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		conn.start();
		Destination destination = session.createQueue("my-queue3");
		MessageConsumer consumer=session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				
				TextMessage tm = (TextMessage) message;
				
				try {
					System.out.println("监听到的消息"+tm.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		

		session.commit();
		session.close();
		conn.close();
		
	}

}

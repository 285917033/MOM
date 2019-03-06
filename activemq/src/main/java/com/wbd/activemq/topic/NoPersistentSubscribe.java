package com.wbd.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 订阅者
 * 
 * @author jwh
 *
 */
public class NoPersistentSubscribe {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		Connection conn = cf.createConnection();
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		conn.start();
		Destination destination = session.createTopic("my-topic");
		MessageConsumer consumer = session.createConsumer(destination);
		TextMessage tm = (TextMessage) consumer.receive();
		while(tm!=null) {
			
			 System.out.println("订阅者收到的消息"+tm.getText());
			 tm=(TextMessage) consumer.receive();
			
		}

		session.commit();
		session.close();
		conn.close();

	}

}

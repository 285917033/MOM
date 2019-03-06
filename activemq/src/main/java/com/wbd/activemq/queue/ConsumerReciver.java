package com.wbd.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
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
		Destination destination = session.createQueue("my-queue");
		MessageConsumer consumer=session.createConsumer(destination);
		int i=0;
		while(i<5) {
			TextMessage tm  =(TextMessage) consumer.receive();
			//设置消息属性,可选， 消息包含， 消息头，消息体， 消息属性
		System.out.println("消息属性 name="+tm.getStringProperty("name"));
		System.out.println("消息属性 age ="+tm.getIntProperty("age"));
			
			//System.out.println("收到的消息"+tm.getText());
			i++;
		}

		session.commit();
		session.close();
		conn.close();
		
	}

}

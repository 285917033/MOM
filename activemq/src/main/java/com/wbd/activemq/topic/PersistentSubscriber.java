package com.wbd.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 订阅者:持久化，如果实现持久化， 订阅者必须先执行，注册到activemq中，设置一个id
 * 
 * @author jwh
 *
 */
public class PersistentSubscriber {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		Connection conn = cf.createConnection();
		//消费者先执行到activemq中注册一个id为s1，id自定义
		conn.setClientID("client1");
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		conn.start();
		Topic topic = session.createTopic("persistent-topic");
		//订阅者的 目的地，名称(自定义)
		TopicSubscriber consumer = session.createDurableSubscriber(topic, "s1");
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

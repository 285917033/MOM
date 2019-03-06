package com.wbd.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * pub/sub 发布订阅模式， 采用topic为消息存储
 * 默认消息为非持久化， 是为了提高性能， 但是非持久化后，订阅者如果不在线， 
 * 消息就无法接收到，即使之后上线，之前发的消息也不会接收到， 只有之后的消息才能接收到
 * 发布者
 * @author jwh
 *
 */
public class NoPersistentPublisher {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		Connection conn = cf.createConnection();
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建一个topic
		Destination destination = session.createTopic("my-topic");
		// 创建一个sender
		MessageProducer producer = session.createProducer(destination);

		for (int i = 0; i < 5; i++) {
			TextMessage tm = session.createTextMessage("topic message=" + i);
			producer.send(tm);

		}
		session.commit();
		session.close();
		conn.close();

	}

}

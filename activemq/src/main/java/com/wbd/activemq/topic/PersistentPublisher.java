package com.wbd.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * pub/sub 发布订阅模式， 采用topic为消息存储
 * 持久化， 离线或者在线都可以收到信息
 * 发布者
 * @author jwh
 *
 */
public class PersistentPublisher {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		Connection conn = cf.createConnection();
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// 创建一个topic
		Destination destination = session.createTopic("persistent-topic");
		// 创建一个sender
		MessageProducer producer = session.createProducer(destination);
		
		//设置消息发送传递模式,为持久化
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);

		for (int i = 0; i < 5; i++) {
			TextMessage tm = session.createTextMessage("persistent message3=" + i);
			producer.send(tm);

		}
		session.commit();
		session.close();
		conn.close();

	}

}

package com.wbd.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * pub/sub ��������ģʽ�� ����topicΪ��Ϣ�洢
 * Ĭ����ϢΪ�ǳ־û��� ��Ϊ��������ܣ� ���Ƿǳ־û��󣬶�������������ߣ� 
 * ��Ϣ���޷����յ�����ʹ֮�����ߣ�֮ǰ������ϢҲ������յ��� ֻ��֮�����Ϣ���ܽ��յ�
 * ������
 * @author jwh
 *
 */
public class NoPersistentPublisher {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		Connection conn = cf.createConnection();
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// ����һ��topic
		Destination destination = session.createTopic("my-topic");
		// ����һ��sender
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

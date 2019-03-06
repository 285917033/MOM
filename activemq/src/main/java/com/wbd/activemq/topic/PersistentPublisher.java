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
 * pub/sub ��������ģʽ�� ����topicΪ��Ϣ�洢
 * �־û��� ���߻������߶������յ���Ϣ
 * ������
 * @author jwh
 *
 */
public class PersistentPublisher {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		Connection conn = cf.createConnection();
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		// ����һ��topic
		Destination destination = session.createTopic("persistent-topic");
		// ����һ��sender
		MessageProducer producer = session.createProducer(destination);
		
		//������Ϣ���ʹ���ģʽ,Ϊ�־û�
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

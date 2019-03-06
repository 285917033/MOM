package com.wbd.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ������:�־û������ʵ�ֳ־û��� �����߱�����ִ�У�ע�ᵽactivemq�У�����һ��id
 * 
 * @author jwh
 *
 */
public class PersistentSubscriber {

	public static void main(String[] args) throws Exception {

		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		Connection conn = cf.createConnection();
		//��������ִ�е�activemq��ע��һ��idΪs1��id�Զ���
		conn.setClientID("client1");
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		conn.start();
		Topic topic = session.createTopic("persistent-topic");
		//�����ߵ� Ŀ�ĵأ�����(�Զ���)
		TopicSubscriber consumer = session.createDurableSubscriber(topic, "s1");
		TextMessage tm = (TextMessage) consumer.receive();
		while(tm!=null) {
			
			 System.out.println("�������յ�����Ϣ"+tm.getText());
			 tm=(TextMessage) consumer.receive();
			
		}

		session.commit();
		session.close();
		conn.close();

	}

}

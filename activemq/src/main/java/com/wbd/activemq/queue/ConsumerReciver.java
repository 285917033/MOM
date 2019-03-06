package com.wbd.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ������
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
			//������Ϣ����,��ѡ�� ��Ϣ������ ��Ϣͷ����Ϣ�壬 ��Ϣ����
		System.out.println("��Ϣ���� name="+tm.getStringProperty("name"));
		System.out.println("��Ϣ���� age ="+tm.getIntProperty("age"));
			
			//System.out.println("�յ�����Ϣ"+tm.getText());
			i++;
		}

		session.commit();
		session.close();
		conn.close();
		
	}

}

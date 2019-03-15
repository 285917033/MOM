package com.wbd.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

//PTP����ģʽ,��Ϣ�ṩ�ߣ�
public class ProducerSender {

	public static void main(String[] args) throws Exception {

		// 1.�������ӹ���
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		// 2.�������Ӷ���
		Connection conn = cf.createConnection();
		conn.start();
		//3.����session����
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//4.��������,һ����Ϣ���Է�������������У���������ö��Ÿ���
		Destination destination = session.createQueue("my-queue3,my-queue4");
		
		//5.�����ṩ�ߣ�����Ϊ������Ϣ
		MessageProducer messageProducer = session.createProducer(destination);
		for(int i=0;i<5;i++) {
		
		//6.������Ϣ����
			TextMessage tm = session.createTextMessage("queue2 message="+i);
			//������Ϣ����,��ѡ�� ��Ϣ������ ��Ϣͷ����Ϣ�壬 ��Ϣ����
		//	tm.setStringProperty("name", "zgh");
		//	tm.setIntProperty("age", 20);
		//7.������Ϣ
			messageProducer.send(tm);
			
			
		}
		
		session.commit();
		session.close();
		conn.close();
	}

}

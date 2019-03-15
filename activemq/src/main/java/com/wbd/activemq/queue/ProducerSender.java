package com.wbd.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

//PTP队列模式,消息提供者，
public class ProducerSender {

	public static void main(String[] args) throws Exception {

		// 1.创建连接工厂
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://192.168.1.81:61616");
		// 2.创建连接对象
		Connection conn = cf.createConnection();
		conn.start();
		//3.创建session对象
		Session session = conn.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//4.创建对列,一个信息可以发布到多个队列中，多个队列用逗号隔开
		Destination destination = session.createQueue("my-queue3,my-queue4");
		
		//5.创建提供者，作用为发送消息
		MessageProducer messageProducer = session.createProducer(destination);
		for(int i=0;i<5;i++) {
		
		//6.创建消息对象
			TextMessage tm = session.createTextMessage("queue2 message="+i);
			//设置消息属性,可选， 消息包含， 消息头，消息体， 消息属性
		//	tm.setStringProperty("name", "zgh");
		//	tm.setIntProperty("age", 20);
		//7.发送消息
			messageProducer.send(tm);
			
			
		}
		
		session.commit();
		session.close();
		conn.close();
	}

}

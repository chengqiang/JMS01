/**
 * This document and its contents are protected by copyright 2012 and owned by Melot Inc.
 * The copying and reproduction of this document and/or its content (whether wholly or partly) or any
 * incorporation of the same into any other material in any media or format of any kind is strictly prohibited.
 * All rights are reserved.
 *
 * Copyright (c) Melot Inc. 2015
 */
package com.activemq2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Title: JMSConsumer
 * <p>
 * Description: ����������-��Ϣ������1
 * </p>
 * 
 * @author ��ǿ<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015��11��17�� ����5:44:04
 */
public class JMSConsumer {
	public static final String USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;// Ĭ�������û���
	public static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;// Ĭ����������
	public static final String BROKERURL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;// Ĭ�����ӵ�ַ
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;// ���ӹ���
		Connection connection = null;// ����
		Session session;// ���ͻ������Ϣ�̻߳ػ�
		Destination destination;// ��ϢĿ�ĵ�
		MessageConsumer messageConsumer;//��Ϣ������
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD,BROKERURL);
		try {
			connection=connectionFactory.createConnection();// ͨ�����ӹ�����ȡ����
			connection.start();// ��������
			session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);// ����session
			//destination = session.createQueue("FirstQueue1");// �������ӵ���Ϣ����
			destination =session.createTopic("FirstTopic1");
			messageConsumer=session.createConsumer(destination);// ������Ϣ������
			messageConsumer.setMessageListener(new Listener());// ������1
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

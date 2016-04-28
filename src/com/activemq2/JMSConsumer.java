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
 * Description: 创建消费者-消息订阅者1
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年11月17日 下午5:44:04
 */
public class JMSConsumer {
	public static final String USERNAME = ActiveMQConnectionFactory.DEFAULT_USER;// 默认链接用户名
	public static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;// 默认链接密码
	public static final String BROKERURL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;// 默认链接地址
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;// 链接工厂
		Connection connection = null;// 链接
		Session session;// 发送或接受消息线程回话
		Destination destination;// 消息目的地
		MessageConsumer messageConsumer;//消息消费者
		connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD,BROKERURL);
		try {
			connection=connectionFactory.createConnection();// 通过链接工厂获取链接
			connection.start();// 启动链接
			session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);// 创建session
			//destination = session.createQueue("FirstQueue1");// 创建链接的消息队列
			destination =session.createTopic("FirstTopic1");
			messageConsumer=session.createConsumer(destination);// 创建消息消费者
			messageConsumer.setMessageListener(new Listener());// 订阅者1
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

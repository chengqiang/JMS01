/*
 * Copyright 2016 Melot.cn All right reserved. This software is the confidential and proprietary information of melot.cn
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Melot.cn.
 */
package com.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 类JMSConsumer3.java的实现描述：消息的生产者（发送者） 
 * 
 * @author Administrator 2016年4月28日 上午11:18:25
 */
public class JMSConsumer3 {

    // 默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    // 默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    // 默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    // 发送的消息数量
    private static final int    SENDNUM  = 10;

    public static void main(String[] args) {
        // 连接工厂
        ConnectionFactory connectionFactory;
        // 连接
        Connection connection = null;
        // 会话 接受或者发送消息的线程
        Session session;
        // 消息的目的地
        Destination destination;
        // 消息生产者
        MessageProducer messageProducer;
        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JMSConsumer3.USERNAME, JMSConsumer3.PASSWORD,JMSConsumer3.BROKEURL);
        try {
            // 通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 创建一个名称为HelloWorld的消息队列
            destination = session.createQueue("HelloWorld");
            // 创建消息生产者
            messageProducer = session.createProducer(destination);
            // 发送消息
            sendMessage(session, messageProducer);

            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送消息
     * @param session
     * @param messageProducer 消息生产者
     * @throws Exception
     */
    public static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
        for (int i = 0; i < JMSConsumer3.SENDNUM; i++) {
            // 创建一条文本消息
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" + i);
            System.out.println("发送消息：Activemq 发送消息" + i);
            // 通过消息生产者发出消息
            messageProducer.send(message);
        }

    }
}

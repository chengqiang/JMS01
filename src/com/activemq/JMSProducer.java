/**
 * This document and its contents are protected by copyright 2012 and owned by Melot Inc. The copying and reproduction
 * of this document and/or its content (whether wholly or partly) or any incorporation of the same into any other
 * material in any media or format of any kind is strictly prohibited. All rights are reserved. Copyright (c) Melot Inc.
 * 2015
 */
package com.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Title: JMSProducer
 * <p>
 * Description:消息生产者
 * </p>
 * 
 * @author 程强<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015年11月17日 下午5:10:04
 */
public class JMSProducer {

    public static final String USERNAME  = ActiveMQConnectionFactory.DEFAULT_USER;       // 默认链接用户名
    public static final String PASSWORD  = ActiveMQConnectionFactory.DEFAULT_PASSWORD;   // 默认链接密码
    public static final String BROKERURL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL; // 默认链接地址
    public static final int    msgCount  = 10;                                           // 发送消息数量

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory;// 链接工厂
        Connection connection = null;// 链接
        Session session;// 发送或接受消息线程回话
        Destination destination;// 消息目的地
        MessageProducer messageProducer;// 消息生产者

        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);

        try {
            connection = connectionFactory.createConnection();// 通过链接工厂获取链接
            connection.start();// 启动链接
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);// 创建session
            destination = session.createQueue("FirstQueue1");// 创建消息队列
            messageProducer = session.createProducer(destination);// 创建消息生产者【目的地】
            // test
            // 发送消息
            sendMsg(session, messageProducer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void sendMsg(Session session, MessageProducer messageProducer) throws Exception {
        for (int i = 0; i < msgCount; i++) {
            TextMessage textMessage = session.createTextMessage("ActiveMQ发送消息" + i);
            System.out.println("发送消息:" + "ActiveMQ发送消息:-==>" + i);
            messageProducer.send(textMessage);
        }
    }
}

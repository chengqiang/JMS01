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
 * Description:��Ϣ������
 * </p>
 * 
 * @author ��ǿ<a href="mailto:qiang.cheng@melot.cn">
 * @version V1.0
 * @since 2015��11��17�� ����5:10:04
 */
public class JMSProducer {

    public static final String USERNAME  = ActiveMQConnectionFactory.DEFAULT_USER;       // Ĭ�������û���
    public static final String PASSWORD  = ActiveMQConnectionFactory.DEFAULT_PASSWORD;   // Ĭ����������
    public static final String BROKERURL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL; // Ĭ�����ӵ�ַ
    public static final int    msgCount  = 10;                                           // ������Ϣ����

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory;// ���ӹ���
        Connection connection = null;// ����
        Session session;// ���ͻ������Ϣ�̻߳ػ�
        Destination destination;// ��ϢĿ�ĵ�
        MessageProducer messageProducer;// ��Ϣ������

        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);

        try {
            connection = connectionFactory.createConnection();// ͨ�����ӹ�����ȡ����
            connection.start();// ��������
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);// ����session
            destination = session.createQueue("FirstQueue1");// ������Ϣ����
            messageProducer = session.createProducer(destination);// ������Ϣ�����ߡ�Ŀ�ĵء�
            // test
            // ������Ϣ
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
            TextMessage textMessage = session.createTextMessage("ActiveMQ������Ϣ" + i);
            System.out.println("������Ϣ:" + "ActiveMQ������Ϣ:-==>" + i);
            messageProducer.send(textMessage);
        }
    }
}

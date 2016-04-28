/*
 * Copyright 2016 Melot.cn All right reserved. This software is the confidential and proprietary information of melot.cn
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with Melot.cn.
 */
package com.activemq2;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ��JMSConsumer3.java��ʵ��������TODO ��ʵ������
 * 
 * @author Administrator 2016��4��28�� ����10:54:57
 */
public class JMSConsumer3 {

    public static void get() {
        try {
            String url = "tcp://localhost:61616";
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            // �����û��������룬����û�����������confĿ¼�µ�credentials.properties�ļ��У�Ҳ������activemq.xml������
            connectionFactory.setUserName("system");
            connectionFactory.setPassword("manager");
            // ��������
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // ����Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // ����Ŀ�꣬�ʹ�������Ҳ���Դ�������
            Destination destination = session.createQueue("test");
            // ������Ϣ������
            MessageConsumer consumer = session.createConsumer(destination);
            // ������Ϣ��������������Ϣ�ĳ�ʱʱ�䣬Ϊ0�Ļ��򲻳�ʱ��receive������һ����Ϣ�����ǳ�ʱ�˻��������߱��رգ�����null
            Message message = consumer.receive(1000);
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            } else {
                System.out.println("Received: " + message);
            }
            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        get();
    }
}

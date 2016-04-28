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
 * 类JMSConsumer3.java的实现描述：TODO 类实现描述
 * 
 * @author Administrator 2016年4月28日 上午10:54:57
 */
public class JMSConsumer3 {

    public static void get() {
        try {
            String url = "tcp://localhost:61616";
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            // 设置用户名和密码，这个用户名和密码在conf目录下的credentials.properties文件中，也可以在activemq.xml中配置
            connectionFactory.setUserName("system");
            connectionFactory.setPassword("manager");
            // 创建连接
            Connection connection = connectionFactory.createConnection();
            connection.start();
            // 创建Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建目标，就创建主题也可以创建队列
            Destination destination = session.createQueue("test");
            // 创建消息消费者
            MessageConsumer consumer = session.createConsumer(destination);
            // 接收消息，参数：接收消息的超时时间，为0的话则不超时，receive返回下一个消息，但是超时了或者消费者被关闭，返回null
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

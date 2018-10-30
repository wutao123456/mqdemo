package com.dlh.filter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息过滤器演示
 */
public class Producer {

    // 单例模式

    // 1、连接工厂
    private ConnectionFactory connectionFactory;
    // 2、连接对象
    private Connection connection;
    // 3、Session对象
    private Session session;
    // 4、生产者
    private MessageProducer messageProducer;

    public Producer() {

        try {
            this.connectionFactory = new ActiveMQConnectionFactory("wutao",
                    "123456", "tcp://localhost:61616");
            this.connection = connectionFactory.createConnection();
            this.connection.start();
            // 设置自动签收模式
            this.session = this.connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            this.messageProducer = this.session.createProducer(null);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }

    public Session getSession() {
        return this.session;
    }

    public void send1(/* String QueueName, Message message */) {
        try {

            Destination destination = this.session.createQueue("first");
            MapMessage msg1 = this.session.createMapMessage();
            msg1.setString("name", "张三");
            msg1.setInt("age", 20);
            // 设置用于消息过滤器的条件
            msg1.setStringProperty("name", "张三");
            msg1.setIntProperty("age", 20);
            msg1.setStringProperty("color", "bule");

            MapMessage msg2 = this.session.createMapMessage();
            msg2.setString("name", "李四");
            msg2.setInt("age", 25);
            // 设置用于消息过滤器的条件
            msg2.setStringProperty("name", "李四");
            msg2.setIntProperty("age", 25);
            msg2.setStringProperty("color", "white");

            MapMessage msg3 = this.session.createMapMessage();
            msg3.setString("name", "赵六");
            msg3.setInt("age", 30);
            // 设置用于消息过滤器的条件
            msg3.setStringProperty("name", "赵六");
            msg3.setIntProperty("age", 30);
            msg3.setStringProperty("color", "bl");

            // 发送消息
            //1.发送的消息,2.是否持久化,3.优先级,4.消息过期时间
            //消息优先级从 0-9 十个级别，0-4 是普通消息，5-9 是加急消息。如果不指定优先级，则默认为 4
            this.messageProducer.send(destination, msg1,
                    DeliveryMode.NON_PERSISTENT, 4, 1000 * 60 * 10);
            this.messageProducer.send(destination, msg2,
                    DeliveryMode.NON_PERSISTENT, 4, 1000 * 60 * 10);
            this.messageProducer.send(destination, msg3,
                    DeliveryMode.NON_PERSISTENT, 4, 1000 * 60 * 10);

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    public void send2() {
        try {
            Destination destination = this.session.createQueue("first");
            TextMessage message = this.session.createTextMessage("我是一个字符串");
            // 发送消息
            this.messageProducer.send(destination, message,
                    DeliveryMode.NON_PERSISTENT, 4, 1000 * 60 * 10);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.send1();

    }

}


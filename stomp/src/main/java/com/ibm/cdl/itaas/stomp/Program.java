package com.ibm.cdl.itaas.stomp;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * https://www.ibm.com/developerworks/cn/opensource/os-cn-rabbit-mq/
 */
public class Program {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.200");
        factory.setUsername("test");
        factory.setPassword("test");
        factory.setVirtualHost("wutao_test");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("test.exchange", "topic");
        String routingKey = "xl.test";
//        String message = "{\"name\":1200,\"name\":\"Welcome to RabbitMQ message push!\"}";
        String message = "hello wutao xl";
        channel.basicPublish("test.exchange", routingKey, null, message.getBytes());
        System.out.println("[x] Sent Message:"+message);
        channel.close();
        connection.close();
    }

}
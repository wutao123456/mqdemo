package com.dlh.mqtt;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/3/31 20:37
 */
public class MqttSender {

    public static void main(String[] args) {
        try {
            testSendMqtt();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void testSendMqtt() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");
        // factory.setVirtualHost("");
        factory.setHost("192.168.0.200");
        factory.setPort(5672);
        Connection conn = null;
        Channel channel = null;
        try {
            conn = factory.newConnection();
            channel = conn.createChannel();
//            String sessionId = getSessionId();
            byte[] messageBodyBytes = "{'text':'Hello, world!中文'}".getBytes();
            // 这样就可以做到点对点通信了，amq.topic是默认exchange
            //https://www.rabbitmq.com/mqtt.html  How it Works
            //MQTT用斜线,AMQP用点
            channel.basicPublish("amq.topic", ".topic.test", null, messageBodyBytes);
        }finally {
            if (channel != null) {
                channel.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}

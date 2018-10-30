package com.dlh.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSConsumer {

    public static void main(String[] args) {
        ConnectionFactory factory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer consumer;

        //第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码、以及要连接的地址，均使用默认即可，默认端口为"tcp://localhost:61616"
        factory = new ActiveMQConnectionFactory("wutao","123456",
                "tcp://localhost:61616");
        try {
            //第二步：通过ConnectionFactory工厂对象我们创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的。
            connection=factory.createConnection();  // 通过连接工厂获取连接
//            connection.setClientID("wm5921");
            connection.start(); // 启动连接
            //第三步：通过Connection对象创建Session会话（上下文环境对象），
            // 用于接收消息，参数配置1为是否启用是事务，参数配置2为签收模式，一般我们设置自动签收
            session=connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session
            //第四步：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，
            // 在PTP模式中，Destination被称作Queue即队列；在Pub/Sub模式，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic。
             destination=session.createQueue("FirstQueue");  // 创建连接的消息队列
//            Topic topic=session.createTopic("wutao_test");
            //持久订阅,不会漏掉任何信息(需唯一clientID)
//            TopicSubscriber subs = session.createDurableSubscriber(topic,"wm5921");
            consumer=session.createConsumer(destination); // 创建消息消费者
//            consumer.setMessageListener(new Listener());
//            subs.setMessageListener(new Listener()); // 注册消息监听

            while(true){
                TextMessage textMessage=(TextMessage)consumer.receive(100000);
                if(textMessage!=null){
                    System.out.println("收到的消息："+textMessage.getText());
                }else{
                    break;
                }
            }// 构造从工厂得到连接对象
            connection = factory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);
            while (true) {
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }
}

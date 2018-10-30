package com.dlh.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/10/29 21:18
 */
public class JMSProducer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        Destination destination;
        MessageProducer producer;

        //第一步：建立ConnectionFactory工厂对象，需要填入用户名、密码、以及要连接的地址，均使用默认即可，默认端口为"tcp://localhost:61616"
        connectionFactory = new ActiveMQConnectionFactory("wutao","123456",
                "tcp://localhost:61616");
        try{
            //第二步：通过ConnectionFactory工厂对象我们创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的。
            connection = connectionFactory.createConnection();
            connection.start();
            //第三步：通过Connection对象创建Session会话（上下文环境对象），
            // 用于接收消息，参数配置1为是否启用是事务，如果为true，则会忽略第二个参数,自动被jms服务器设置为SESSION_TRANSACTED。
            // 参数配置2为签收模式，一般我们设置自动签收。
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //第四步：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，
            // 在PTP模式中，Destination被称作Queue即队列；在Pub/Sub模式，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic。
            destination = session.createQueue("FirstQueue");
            //第五步：我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者）MessageProducer/MessageConsumer。
            producer = session.createProducer(destination);
            //第六步：我们可以使用MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性（DeliveryMode），我们稍后详细介绍。
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //第七步：最后我们使用JMS规范的TextMessage形式创建数据（通过Session对象），
            // 并用MessageProducer的send方法发送数据。同理客户端使用receive方法进行接收数据。最后不要忘记关闭Connection连接。
            sendMessage(session,producer);
            //若前面开启事务，下面就必须commit,否则收不到消息
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void sendMessage(Session session,MessageProducer producer)throws Exception{
        for(int i=0;i<5;i++){
            TextMessage message = session.createTextMessage("ActiveMQ发送的topic"+i);
            System.out.println("发送消息：" + "ActiveMq 发送的topic" + i);
            producer.send(message);
        }
    }
}

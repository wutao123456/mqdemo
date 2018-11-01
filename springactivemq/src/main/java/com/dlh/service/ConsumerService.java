package com.dlh.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/1 21:25
 */
@Service
public class ConsumerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination) {
        ActiveMQQueue queue = (ActiveMQQueue) destination;
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        try {
            System.out.println("从队列" + queue.getQueueName() + "收到了消息：\t"
                    + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return textMessage;
    }
}

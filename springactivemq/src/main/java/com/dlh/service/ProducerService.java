package com.dlh.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Topic;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/1 21:23
 */
@Service
public class ProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier(value = "jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    public void sendMessage(String message){
        ActiveMQQueue queue = (ActiveMQQueue) jmsTemplate.getDefaultDestination();
        String queueName = null;
        try{
             queueName = queue.getQueueName();
        }catch (JMSException e){

        }
        System.out.println(Thread.currentThread().getName()+" 向队列"+queueName+"发送消息---------------------->"+message);
        jmsTemplate.convertAndSend(message);
    }

    public void sendTopicMessage(Topic topic,String message){
        String topicName = null;
        try{
            topicName = topic.getTopicName();
        }catch (JMSException e){

        }
        System.out.println(Thread.currentThread().getName()+" 向主题"+topicName+"发送消息---------------------->"+message);
        jmsTopicTemplate.convertAndSend(topic,message);
    }
}

package com.dlh.pubsub;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/10/29 22:34
 * 这里演示消息订阅模式
 */
public class Listener2 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        // TODO Auto-generated method stub
        try {
            System.out.println("订阅者二收到的消息：" + ((TextMessage) message).getText());
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}


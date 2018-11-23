package com.dlh.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/11/22 23:48
 */
public class ProducerDemo {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.200:9092");
        props.put("acks", "all");//我们指定了“all”将会阻塞消息，这种设置性能最低，但是是最可靠的。
        props.put("retries", 0);//消息失败,生产者自动重试,但会有消息重复的可能性
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);//延迟发送(延迟时间)
        props.put("buffer.memory", 33554432);//生产者可用的缓存总量
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer<String, String>(props);
        for(int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("wutao", Integer.toString(i), "wutao_test=====>" + i));
        }
        producer.close();
    }
}

package com.yaojia.mq.product;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:延迟消息生产者
 **/
public class RocketMQSysDelayedProduct {
    private static DefaultMQProducer producer;

    static {
        producer = new DefaultMQProducer("order_product_group");

        producer.setNamesrvAddr("192.168.43.139:9876");

        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里设置了消息为延迟消息
     * 
     * @param topic
     * @param message
     */
    public static void sendMessage(String topic, String message) throws UnsupportedEncodingException {

        Message msg = new Message(topic, "", message.getBytes(RemotingHelper.DEFAULT_CHARSET));
        // 这里设置了消息为延迟消息，消费级别为3,30分钟
        msg.setDelayTimeLevel(16);
        try {
            SendResult sendResult = producer.send(msg);
            System.out.println("%s%n" + sendResult);
        } catch (Exception e) {

        }
    }
}

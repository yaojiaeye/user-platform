package com.yaojia.mq.product;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * mq生产者
 *
 * @Author : yaojia
 * @Create: 2021/5/20
 */
public class RocketMQSysProduct {

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
     *
     * @param topic
     * @param message
     */
    public static void sendMessage(String topic, String message) throws UnsupportedEncodingException {

        Message msg = new Message(
                topic,
                "",
                message.getBytes(RemotingHelper.DEFAULT_CHARSET)
        );

        try {
            SendResult sendResult = producer.send(msg);
            System.out.println("%s%n"+sendResult);
        } catch (Exception e) {

        }
    }
}

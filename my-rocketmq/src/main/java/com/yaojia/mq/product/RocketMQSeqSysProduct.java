package com.yaojia.mq.product;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:实现消息的顺序消费
 **/
public class RocketMQSeqSysProduct {

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
     * 把同一订单消息发送到同一MessageQueue中
     * 
     * @param topic
     * @param message
     */
    public static void sendMessage(String topic, String message) throws UnsupportedEncodingException {

        Message msg = new Message(topic, "", message.getBytes(RemotingHelper.DEFAULT_CHARSET));
        long orderId = 0;
        try {
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    long orderId = (long)arg;
                    long index = orderId % mqs.size();
                    return mqs.get((int)index);
                }
            }, orderId);
            // System.out.println("%s%n" + sendResult);
        } catch (Exception e) {

        }
    }
}

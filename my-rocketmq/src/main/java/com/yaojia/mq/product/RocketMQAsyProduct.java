package com.yaojia.mq.product;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

/**
 * mq生产者
 *
 * @Author : yaojia
 * @Create: 2021/5/20
 */
public class RocketMQAsyProduct {

    private static DefaultMQProducer producer;

    static {
         producer = new DefaultMQProducer("order_product_group");

        producer.setNamesrvAddr("192.168.43.139:9876");

        try {
            producer.start();

            // 设置异步发送消息失败时候重试的次数
            producer.setRetryTimesWhenSendAsyncFailed(0);
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
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("%s%n"+sendResult);
                }
                @Override
                public void onException(Throwable throwable) {
                    System.out.println("%s%n"+throwable);
                }
            });
        } catch (Exception e) {

        }
    }
}

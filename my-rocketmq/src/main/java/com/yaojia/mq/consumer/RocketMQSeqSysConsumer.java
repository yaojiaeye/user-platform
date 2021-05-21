package com.yaojia.mq.consumer;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import lombok.SneakyThrows;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxx
 **/
public class RocketMQSeqSysConsumer {

    /**
     * mq消费消息
     */
    public static void start() {
        new Thread() {
            @SneakyThrows
            @Override
            public void run() {

                DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_product_group");
                consumer.setNamesrvAddr("192.168.43.139:9876");
                consumer.subscribe("TopicOrderPaySuccess", "*");
                consumer.registerMessageListener(new MessageListenerConcurrently() {
                    @Override
                    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                        ConsumeConcurrentlyContext context) {
                        try {
                            for (MessageExt msg : msgs) {

                            }
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        } catch (Exception e) {
                            // return Susp
                        }
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                });
                consumer.start();
                System.out.printf("Consumer Started.%n");
            }
        }.start();
    }
}

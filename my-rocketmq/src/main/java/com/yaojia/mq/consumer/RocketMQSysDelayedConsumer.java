package com.yaojia.mq.consumer;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:延迟消息消费者
 **/
public class RocketMQSysDelayedConsumer {

    /**
     * 延迟消息消费者
     */
    public static void start() {
        new Thread() {
            @Override
            public void run() {
                try {
                    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_product_group");
                    consumer.setNamesrvAddr("192.168.43.139:9876");
                    consumer.subscribe("TopicOrderPaySuccess", "*");
                    consumer.registerMessageListener(new MessageListenerConcurrently() {
                        @Override
                        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                            ConsumeConcurrentlyContext context) {
                            for (MessageExt msg : msgs) {
                                String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(),
                                    body);
                            }
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    });
                    consumer.start();

                    System.out.printf("Consumer Started.%n");
                } catch (Exception e) {

                }
            }
        }.start();
    }
}

package com.yaojia.mq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author : yaojia
 * @Create: 2021/5/20
 */
public class RocketMQAsyConsumer {


    /**
     * mq消费消息
     */
    public static void start(){
        new Thread(){
            @Override
            public void run() {
                try{
                    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_product_group");
                    consumer.setNamesrvAddr("192.168.43.139:9876");
                    consumer.subscribe("TopicOrderPaySuccess", "*");
                    consumer.registerMessageListener(new MessageListenerConcurrently() {
                        @Override
                        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                        ConsumeConcurrentlyContext context) {
                            for (MessageExt msg : msgs) {
                                String body = new String(msg.getBody(), StandardCharsets.UTF_8);
                                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), body);
                            }
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    });
                    consumer.start();

                    System.out.printf("Consumer Started.%n");
                }catch (Exception e){

                }
            }
        }.start();
    }
}

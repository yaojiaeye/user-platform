package com.yaojia.mq.api.reform.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yaojia.mq.api.reform.listener.OrderHotelMessageListener;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:订单消息消费者
 **/
@Configuration
public class OrderDelayConsumerConfiguration {

    /**
     * namesrv address
     */
    @Value("${rocketmq.namesrv.address}")
    private String namesrvAddress;

    /**
     * 登录topic
     */
    @Value("${rocketmq.order.delay.topic}")
    private String orderDelayTopic;

    @Value("${rocketmq.order.delay.consumer.group}")
    private String orderDelayConsumerGroup;

    /**
     * 订单延时消息的consumer bean
     *
     * @return 订单延时消息的consumer bean
     */
    @Bean(value = "orderDelayConsumer")
    public DefaultMQPushConsumer orderConsumer(OrderHotelMessageListener orderDelayMessageListener)
        throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(orderDelayConsumerGroup);
        consumer.setNamesrvAddr(namesrvAddress);
        consumer.subscribe(orderDelayTopic, "*");
        consumer.setMessageListener(orderDelayMessageListener);
        consumer.start();
        return consumer;
    }

}

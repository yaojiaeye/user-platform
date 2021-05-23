package com.yaojia.mq.api.reform.product;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:订单的rocketmq生产者配置类
 **/
@Configuration
public class OrderProducerConfiguration {

    @Value("${rocketmq.namesrv.address}")
    private String namesrvAddress;

    @Value("${rocketmq.order.producer.group}")
    private String orderProducerGroup;

    /**
     * 订单消息生产者
     * 
     * @return
     * @throws MQClientException
     */
    @Bean(value = "orderMqProducer")
    public DefaultMQProducer orderMqProduct() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(orderProducerGroup);
        producer.setNamesrvAddr(namesrvAddress);
        producer.start();
        return producer;
    }

}

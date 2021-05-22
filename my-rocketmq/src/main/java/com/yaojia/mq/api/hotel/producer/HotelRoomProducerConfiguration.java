package com.yaojia.mq.api.hotel.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:酒店客房的rocketmq生产者配置类
 **/
@Configuration
public class HotelRoomProducerConfiguration {

    @Value("${rocketmq.namesrv.address}")
    private String namesrvAddress;

    @Value("${rocketmq.hotelRoom.producer.group}")
    private String hotelRoomProducerGroup;

    /**
     * 登录消息rocketmq的生产者对象
     * 
     * @return
     * @throws MQClientException
     */
    @Bean(value = "hotelRoomMqProducer")
    public DefaultMQProducer hotelRoomMqProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(hotelRoomProducerGroup);
        producer.setNamesrvAddr(namesrvAddress);
        producer.start();
        return producer;
    }
}

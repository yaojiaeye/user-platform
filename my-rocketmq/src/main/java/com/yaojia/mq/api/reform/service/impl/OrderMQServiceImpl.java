package com.yaojia.mq.api.reform.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yaojia.mq.api.reform.dto.OrderInfoDTO;
import com.yaojia.mq.api.reform.service.OrderMQService;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:订单事件通知组件
 **/
@Service
public class OrderMQServiceImpl implements OrderMQService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderMQServiceImpl.class);

    @Autowired
    @Qualifier(value = "orderMqProducer")
    private DefaultMQProducer orderMqProducer;

    /**
     * 订单消息topic
     */
    @Value("${rocketmq.order.topic}")
    private String orderTopic;

    @Override
    public void informPayOrderEvent(OrderInfoDTO orderInfoDTO) {

    }

    /**
     * 发送订单消息
     * 
     * @param orderInfoDTO
     */
    private void sendOrderMessage(OrderInfoDTO orderInfoDTO) {
        Message message = new Message();
        message.setTopic(orderTopic);
        message.setBody(JSON.toJSONString(orderInfoDTO).getBytes(StandardCharsets.UTF_8));
        try {
            orderMqProducer.send(message, new MessageQueueSelector() {

                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object orderId) {
                    Integer id = (Integer)orderId;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderInfoDTO.getId());
            LOGGER.info("order sending rocketmq message param : {}, orderId:{}", JSON.toJSONString(orderInfoDTO),
                orderInfoDTO.getId());
        } catch (Exception e) {
            // 发送订单消息失败
            LOGGER.error("send order message fail,error message:{}", e.getMessage());
        }
    }

}

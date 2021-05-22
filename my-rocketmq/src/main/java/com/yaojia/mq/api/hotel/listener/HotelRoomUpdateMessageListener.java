package com.yaojia.mq.api.hotel.listener;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:
 **/
@Component
public class HotelRoomUpdateMessageListener implements MessageListenerConcurrently {

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelRoomUpdateMessageListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

        for (int i = 0; i < msgs.size(); i++) {
            String body = new String(msgs.get(i).getBody(), StandardCharsets.UTF_8);
            LOGGER.info("yaojia " + i + "receiver room  message roomId:{}", body);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

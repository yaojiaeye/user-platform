package com.yaojia.mq.test;

import java.nio.charset.StandardCharsets;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.yaojia.mq.LittleProjectRocketMqApplication;
import com.yaojia.mq.api.hotel.dto.AdminHotelRoomMessage;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxx
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LittleProjectRocketMqApplication.class})
public class MqTestTest {

    private Logger LOGGER = LoggerFactory.getLogger(MqTestTest.class);

    @Autowired
    @Qualifier(value = "hotelRoomMqProducer")
    private DefaultMQProducer hotelRoomMqProducer;

    @Test
    public void sendRoomUpdateMessage() {
        // 发送广播消息到mq中
        // 提供给小程序的api模块来消费消息后从redis中获取消息更新本地jvm内存
        for (int i = 0; i < 10000; i++) {
            Message message = new Message();
            message.setTopic("hotel_room_topic");
            AdminHotelRoomMessage adminHotelRoomMessage = new AdminHotelRoomMessage();
            adminHotelRoomMessage.setRoomId(123456l + i);
            adminHotelRoomMessage.setPhoneNumber("13279275746");

            message.setBody(JSON.toJSONString(adminHotelRoomMessage).getBytes(StandardCharsets.UTF_8));
            try {
                LOGGER.info("start send hotel room update  message, param:{}", "");
                SendResult sendResult = hotelRoomMqProducer.send(message);
                LOGGER.info("end send hotel room update  message, param:{}, sendResult:{}", "",
                    JSON.toJSONString(sendResult));
            } catch (Exception e) {
                LOGGER.error("send login success notify message fail, error message:{}", e);
            }
        }
    }

}
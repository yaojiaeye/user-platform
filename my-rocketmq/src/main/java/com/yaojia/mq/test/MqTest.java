package com.yaojia.mq.test;

import com.yaojia.mq.consumer.RocketMQConsumer;
import com.yaojia.mq.product.RocketMQProduct;

import java.io.UnsupportedEncodingException;

/**
 * @Author : yaojia
 * @Create: 2021/5/20
 */
public class MqTest {

    public static void main(String[] args) {
        for (int i=0; i < 10; i++){
            String message = "hello world !!! => " + i;
            try {
                RocketMQProduct.sendMessage("TopicOrderPaySuccess",message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        while (true){
//            RocketMQConsumer.start();
//        }
    }
}

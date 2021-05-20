package com.yaojia.mq.service.impl;

import com.yaojia.mq.pojo.Order;
import com.yaojia.mq.product.RocketMQProduct;
import com.yaojia.mq.service.OrderService;

import java.io.UnsupportedEncodingException;

/**
 *
 * @Author : yaojia
 * @Create: 2021/5/20
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public void updateOrderStatus(Order order) {

        try {
            String message = "hello world !!!";
            RocketMQProduct.sendMessage("TopicOrderPaySuccess",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

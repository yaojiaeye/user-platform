package com.yaojia.mq.service.impl;

import com.yaojia.mq.pojo.Order;
import com.yaojia.mq.product.RocketMQSysProduct;
import com.yaojia.mq.service.OrderService;

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
            RocketMQSysProduct.sendMessage("TopicOrderPaySuccess",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

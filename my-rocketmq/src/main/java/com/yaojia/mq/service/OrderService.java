package com.yaojia.mq.service;

import com.yaojia.mq.pojo.Order;

/**
 * @Author : yaojia
 * @Create: 2021/5/20
 */
public interface OrderService {

    void updateOrderStatus(Order order);
}

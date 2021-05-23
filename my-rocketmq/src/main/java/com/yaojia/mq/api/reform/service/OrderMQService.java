package com.yaojia.mq.api.reform.service;

import com.yaojia.mq.api.reform.dto.OrderInfoDTO;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:订单消息接口
 **/
public interface OrderMQService {

    /**
     * 通知订单支付事件
     *
     * @param orderInfoDTO
     *            订单信息
     */
    void informPayOrderEvent(OrderInfoDTO orderInfoDTO);
}

package com.yaojia.mq.mqdefault;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:xxxx
 **/
public class TransactionListenerImpl implements TransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 如果half消息发送成功了，此时就会回调你的接口，你就可以执行本地事务了
     * 
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            // 执行订单本地事务
            // 接着混根据一练闯的本地执行事务结果，选择混滚或提交
            // 如果本地事务成功了，就返回commit
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            // 如果失败，回滚本地事务，回滚一切事务
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    /**
     * 如果因为各种原因，没有返回commit或rollback
     * 
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}

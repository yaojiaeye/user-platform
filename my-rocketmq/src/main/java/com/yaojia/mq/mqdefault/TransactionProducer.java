package com.yaojia.mq.mqdefault;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author <a href="jiayao:little@163.com">little</a> version: 1.0 Description:rocketmq 事务性发送消息
 **/
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException {
        TransactionListener transactionListener = new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer("TestProductGroup");
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("client-transaction-msg-check-thread");
                    return thread;
                }
            });

        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);

        producer.start();

        Message msg = new Message("TopicTest1234", "TestTag", "TestKey",
            ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET));

        msg.setDelayTimeLevel(1);
        SendResult sendResult = producer.sendMessageInTransaction(msg, null);
        System.out.printf("%s%n", sendResult);
    }
}

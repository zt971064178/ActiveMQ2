package cn.itcast.zt.producer.core;

import cn.itcast.zt.producer.service.QMessageService;
import cn.itcast.zt.producer.tx.MessageTransactionSynchronizationAdapter;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by zhangtian on 2017/6/1.
 */
public class MessageProducer implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    private TransactionMessageProducer transactionMessageProducer;

    @Autowired
    private QMessageService qMessageService;

    // 消息目的地
    private String destName ;
    // 是否开启事务
    private boolean transaction ;
    // 是否持久化消息
    private boolean persistent ;
    // 是否n2级别的消息
    private boolean n2 ;

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public boolean isTransaction() {
        return transaction;
    }

    public void setTransaction(boolean transaction) {
        this.transaction = transaction;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public boolean isN2() {
        return n2;
    }

    public void setN2(boolean n2) {
        this.n2 = n2;
    }

    /**
     * 发送消息
     * @param data
     */
    public void sendMessage(Map<String, String> data) {
        Preconditions.checkArgument(data != null && !data.isEmpty(), "message must not be empty...");

    }

    /**
     * 添加事务同步
     */
    private void transactionSynchronize(){
        MessageTransactionSynchronizationAdapter synchronizationAdapter = new MessageTransactionSynchronizationAdapter() ;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

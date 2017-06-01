package cn.itcast.zt.producer.tx;

import cn.itcast.zt.producer.core.TransactionMessageProducer;
import cn.itcast.zt.producer.domain.QMessage;
import cn.itcast.zt.producer.service.QMessageService;
import cn.itcast.zt.producer.utils.MessageHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 事务同步管理器
 * Created by zhangtian on 2017/6/1.
 */
public class MessageTransactionSynchronizationAdapter extends TransactionSynchronizationAdapter {
    private static final Logger log = LoggerFactory.getLogger(MessageTransactionSynchronizationAdapter.class);

    @Autowired
    private TransactionMessageProducer transactionMessageProducer ;
    @Autowired
    private QMessageService qMessageService ;

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    @Override
    public void afterCompletion(int status) {
        try {
            if(STATUS_COMMITTED == status){
                List<String> messageIds = MessageHolder.get() ;
                sendMessageToBroker(messageIds);
            }else {
                log.warn("事务提交失败，数据库回滚后，清空缓存中的消息：{}", MessageHolder.get());
            }
        }finally {
            MessageHolder.clear();
        }
    }

    /**
     * 向broker中发送消息
     * @param messageIds 消息id集合
     */
    private void sendMessageToBroker(List<String> messageIds) {
        if(CollectionUtils.isEmpty(messageIds)) {
            return ;
        }

        try {
            for (String messageId : messageIds) {
                QMessage qMessage = qMessageService.getQMessage(messageId) ;
                if(qMessage == null){
                    continue;
                }
                transactionMessageProducer.sendMessage(qMessage);
            }
        }catch (Exception e) {
            log.error("send message error:{}", e);
        }
    }
}

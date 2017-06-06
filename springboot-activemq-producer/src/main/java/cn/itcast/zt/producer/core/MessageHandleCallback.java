package cn.itcast.zt.producer.core;

import cn.itcast.zt.producer.service.QMessageService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息处理回调
 * Created by zhangtian on 2017/6/1.
 */
@Component
public class MessageHandleCallback implements MessageCallback {
    private static final Logger log = LoggerFactory.getLogger(MessageHandleCallback.class);

    @Autowired
    private QMessageService qMessageService ;

    /**
     * 事务消息处理成功
     * @param messageId
     */
    @Override
    public void onSuccess(String messageId) {
        Preconditions.checkArgument(StringUtils.isNotBlank(messageId), "messageId must not empty");
        qMessageService.deleteQMessage(messageId) ;
    }

    /**
     * 事务消息处理失败,进行日志记录
     * @param e
     * @param messageId
     */
    @Override
    public void onFail(Exception e, String messageId) {
        log.error("send tx messageId:{},error:{}", messageId, e);
    }
}

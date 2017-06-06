package cn.itcast.zt.producer.schedule;

import cn.itcast.zt.producer.core.TransactionMessageProducer;
import cn.itcast.zt.producer.domain.QMessage;
import cn.itcast.zt.producer.service.QMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangtian on 2017/6/1.
 */
@Component
public class CompensateMessageSchedule {
    private static final Logger log = LoggerFactory.getLogger(CompensateMessageSchedule.class);

    @Autowired
    private QMessageService qMessageService ;

    @Autowired
    private TransactionMessageProducer producer ;

    /**
     * 对于发送失败没有消费的消息，定时重新发送
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void compensateJob() {
        List<QMessage> messages = null;
        try {
            messages = qMessageService.selectAllQMessage(System.currentTimeMillis());
        } catch (Exception e) {
            log.error("query messages error:{}", e);
        }
        // 如果没有消息，放弃执行
        if (messages == null || messages.size() == 0) {
            return;
        }
        for (QMessage message : messages) {
            producer.sendMessage(message);
        }
    }
}

package cn.itcast.zt.producer.service.impl;

import cn.itcast.zt.producer.domain.QMessage;
import cn.itcast.zt.producer.service.QMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息处理
 * Created by zhangtian on 2017/6/1.
 */
@Service
public class QMessageServiceImpl implements QMessageService {

    @Override
    public QMessage getQMessage(String messageId) {
        return null;
    }

    @Override
    public int addQMessage(QMessage qMessage) {
        return 0;
    }

    @Override
    public int updateQMessage(QMessage qMessage) {
        return 0;
    }

    @Override
    public int deleteQMessage(String messageId) {
        return 0;
    }

    @Override
    public List<QMessage> selectAllQMessage(Long currentTime) {
        return null;
    }
}

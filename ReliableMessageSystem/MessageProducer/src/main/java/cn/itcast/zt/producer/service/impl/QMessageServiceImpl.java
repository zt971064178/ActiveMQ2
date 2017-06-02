package cn.itcast.zt.producer.service.impl;

import cn.itcast.zt.producer.domain.QMessage;
import cn.itcast.zt.producer.mapper.QMessageMapper;
import cn.itcast.zt.producer.service.QMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息处理
 * Created by zhangtian on 2017/6/1.
 */
@Service
public class QMessageServiceImpl implements QMessageService {

    @Autowired
    private QMessageMapper qMessageMapper ;

    @Override
    public QMessage getQMessage(String messageId) {
        return qMessageMapper.selectQMessageByMessageId(messageId);
    }

    @Override
    public int addQMessage(QMessage qMessage) {
        return qMessageMapper.addQMessage(qMessage);
    }

    @Override
    public int updateQMessage(QMessage qMessage) {
        return qMessageMapper.updateQMessage(qMessage);
    }

    @Override
    public int deleteQMessage(String messageId) {
        return qMessageMapper.deleteQMessage(messageId);
    }

    @Override
    public List<QMessage> selectAllQMessage(Long currentTime) {
        return qMessageMapper.selectAllQMessage(currentTime);
    }
}

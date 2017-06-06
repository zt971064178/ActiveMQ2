package cn.itcast.zt.producer.service.impl;

import cn.itcast.zt.producer.domain.QMessage;
import cn.itcast.zt.producer.mapper.QMessageMapper;
import cn.itcast.zt.producer.service.QMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        if(StringUtils.isBlank(messageId)) {
            return null ;
        }
        return qMessageMapper.selectQMessageByMessageId(messageId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int addQMessage(QMessage qMessage) {
        if(qMessage == null) {
            return 0 ;
        }
        return qMessageMapper.addQMessage(qMessage);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int updateQMessage(QMessage qMessage) {
        if(qMessage == null) {
            return 0 ;
        }
        return qMessageMapper.updateQMessage(qMessage);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int deleteQMessage(String messageId) {
        if(StringUtils.isBlank(messageId)) {
            return 0 ;
        }
        return qMessageMapper.deleteQMessage(messageId);
    }

    @Override
    public List<QMessage> selectAllQMessage(Long currentTime) {
        if(currentTime == null) {
            return null ;
        }
        return qMessageMapper.selectAllQMessage(currentTime);
    }
}

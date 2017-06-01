package cn.itcast.zt.producer.mapper.impl;

import cn.itcast.zt.producer.domain.QMessage;
import cn.itcast.zt.producer.mapper.QMessageMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangtian on 2017/6/1.
 */
@Repository
public class QMessageMapperImpl implements QMessageMapper {

    @Override
    public QMessage selectQMessageByMessageId(String messageId) {
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

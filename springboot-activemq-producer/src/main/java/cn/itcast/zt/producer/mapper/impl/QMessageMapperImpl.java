package cn.itcast.zt.producer.mapper.impl;

import cn.itcast.zt.producer.domain.QMessage;
import cn.itcast.zt.producer.mapper.QMessageMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangtian on 2017/6/1.
 */
@Repository
public class QMessageMapperImpl implements QMessageMapper {

    @Autowired
    private SqlSession sqlSession ;

    // 根据messageId获取QMessage
    @Override
    public QMessage selectQMessageByMessageId(String messageId) {
        return sqlSession.selectOne("cn.itcast.zt.producer.mapper.QMessageMapper.selectQMessageByMessageId", messageId);
    }

    // 添加消息
    @Override
    public int addQMessage(QMessage qMessage) {
        return sqlSession.insert("cn.itcast.zt.producer.mapper.QMessageMapper.addQMessage", qMessage);
    }

    @Override
    public int updateQMessage(QMessage qMessage) {
        return sqlSession.update("cn.itcast.zt.producer.mapper.QMessageMapper.updateQMessage", qMessage);
    }

    @Override
    public int deleteQMessage(String messageId) {
        return sqlSession.delete("cn.itcast.zt.producer.mapper.QMessageMapper.deleteQMessage", messageId);
    }

    @Override
    public List<QMessage> selectAllQMessage(Long currentTime) {
        return sqlSession.selectList("cn.itcast.zt.producer.mapper.QMessageMapper.selectAllQMessage", currentTime);
    }
}

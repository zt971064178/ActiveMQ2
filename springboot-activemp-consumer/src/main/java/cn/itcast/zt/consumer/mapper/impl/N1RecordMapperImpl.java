package cn.itcast.zt.consumer.mapper.impl;

import cn.itcast.zt.consumer.domain.N1Record;
import cn.itcast.zt.consumer.mapper.N1RecordMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by zhangtian on 2017/6/2.
 */
@Repository
public class N1RecordMapperImpl implements N1RecordMapper {

    @Autowired
    private SqlSession sqlSession ;

    // 根据消息id获取消费记录
    @Override
    public N1Record selectN1Record(String messageId) {
        return sqlSession.selectOne("cn.itcast.zt.consumer.mapper.N1RecordMapper.selectN1Record", messageId);
    }

    // 添加消息消费记录
    @Override
    public int addN1Record(N1Record n1Record) {
        return sqlSession.insert("cn.itcast.zt.consumer.mapper.N1RecordMapper.addN1Record", n1Record);
    }

    // 删除消费记录
    @Override
    public int deleteN1Record(Date timeStamp) {
        return sqlSession.delete("cn.itcast.zt.consumer.mapper.N1RecordMapper.deleteN1Record", timeStamp);
    }
}

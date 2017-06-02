package cn.itcast.zt.consumer.mapper.impl;

import cn.itcast.zt.consumer.domain.N2Record;
import cn.itcast.zt.consumer.mapper.N2RecordMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by zhangtian on 2017/6/2.
 */
public class N2RecordMapperImpl implements N2RecordMapper {

    @Autowired
    private SqlSession sqlSession ;

    // 根据业务标识查询N2类型的记录
    @Override
    public N2Record selectN2RecordByMark(Map<String, Object> params) {
        return sqlSession.selectOne("cn.itcast.zt.consumer.mapper.N2RecordMapper.selectN2RecordByMark", params);
    }

    // 根据参数查询N2类型的记录
    @Override
    public N2Record selectN2Recerd(Map<String, Object> params) {
        return sqlSession.selectOne("cn.itcast.zt.consumer.mapper.N2RecordMapper.selectN2Record", params);
    }

    // 添加N2类型的记录
    @Override
    public int addN2Record(N2Record n2Record) {
        return sqlSession.insert("cn.itcast.zt.consumer.mapper.N2RecordMapper.addN2Record", n2Record);
    }

    // 更新N2类型的记录
    @Override
    public int updateN2Record(N2Record n2Record) {
        return sqlSession.update("cn.itcast.zt.consumer.mapper.N2RecordMapper.updateN2Record", n2Record);
    }
}

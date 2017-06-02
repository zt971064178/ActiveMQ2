package cn.itcast.zt.consumer.service.impl;

import cn.itcast.zt.consumer.domain.N2Record;
import cn.itcast.zt.consumer.mapper.N2RecordMapper;
import cn.itcast.zt.consumer.service.N2RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * Created by zhangtian on 2017/6/2.
 */
@Service
public class N2RecordServiceImpl implements N2RecordService {

    @Autowired
    private N2RecordMapper n2RecordMapper ;

    // 根据业务标识查询N2类型的记录
    @Override
    public N2Record selectN2RecordByMark(Map<String, Object> params) {
        if(CollectionUtils.isEmpty(params)) {
            return null ;
        }
        return n2RecordMapper.selectN2RecordByMark(params);
    }

    // 根据参数查询N2类型的记录
    @Override
    public N2Record selectN2Record(Map<String, Object> params) {
        if(CollectionUtils.isEmpty(params)) {
            return null ;
        }
        return n2RecordMapper.selectN2Recerd(params);
    }

    // 添加N2类型的记录
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int addN2Record(N2Record n2Record) {
        if(n2Record == null){
            return 0 ;
        }
        return n2RecordMapper.addN2Record(n2Record);
    }

    // 更新N2类型的记录
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public int updateN2Record(N2Record n2Record) {
        if(n2Record == null){
            return 0 ;
        }
        return n2RecordMapper.updateN2Record(n2Record);
    }
}

package cn.itcast.zt.consumer.service.impl;

import cn.itcast.zt.consumer.domain.N1Record;
import cn.itcast.zt.consumer.mapper.N1RecordMapper;
import cn.itcast.zt.consumer.service.N1RecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by zhangtian on 2017/6/2.
 */
@Service
public class N1RecordServiceImpl implements N1RecordService{

    @Autowired
    private N1RecordMapper n1RecordMapper ;

    // 获取N1Record
    @Override
    public N1Record selectN1Record(String messageId) {
        if(StringUtils.isBlank(messageId)) {
            return null ;
        }
        return n1RecordMapper.selectN1Record(messageId);
    }

    // 添加N1Record
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int addN1Record(N1Record n1Record) {
        if(n1Record == null){
            return 0 ;
        }
        return n1RecordMapper.addN1Record(n1Record);
    }

    // 删除N1Record
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
    public int deleteN1Record(Date timeStamp) {
        if(timeStamp == null){
            return 0 ;
        }
        return n1RecordMapper.deleteN1Record(timeStamp);
    }
}

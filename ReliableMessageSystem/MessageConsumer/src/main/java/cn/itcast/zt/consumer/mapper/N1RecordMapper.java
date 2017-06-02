package cn.itcast.zt.consumer.mapper;

import cn.itcast.zt.consumer.domain.N1Record;

import java.util.Date;

/**
 * Created by zhangtian on 2017/6/2.
 */
public interface N1RecordMapper {

    /**
     * 根据消息id获取消费记录
     * @param messageId 消息id
     * @return
     */
    public N1Record selectN1Record(String messageId) ;

    /**
     * 添加消息消费记录
     * @param n1Record 消费记录
     * @return
     */
    public int addN1Record(N1Record n1Record);

    /**
     * 删除消费记录
     * @param timeStamp 时间戳
     * @return
     */
    public int deleteN1Record(Date timeStamp) ;
}

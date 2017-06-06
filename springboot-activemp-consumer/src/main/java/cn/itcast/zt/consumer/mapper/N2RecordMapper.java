package cn.itcast.zt.consumer.mapper;

import cn.itcast.zt.consumer.domain.N2Record;

import java.util.Map;

/**
 * Created by zhangtian on 2017/6/2.
 */
public interface N2RecordMapper {

    /**
     * 根据业务标识查询N2类型的记录
     * @param params 查询参数
     * @return
     */
    public N2Record selectN2RecordByMark(Map<String, Object> params) ;

    /**
     * 根据参数查询N2类型的记录
     * @param params 查询参数
     * @return
     */
    public N2Record selectN2Recerd(Map<String, Object> params) ;

    /**
     * 添加N2类型的记录
     * @param n2Record N2记录
     * @return
     */
    public int addN2Record(N2Record n2Record) ;

    /**
     * 更新N2类型的记录
     * @param n2Record N2类型的记录
     * @return
     */
    public int updateN2Record(N2Record n2Record) ;
}

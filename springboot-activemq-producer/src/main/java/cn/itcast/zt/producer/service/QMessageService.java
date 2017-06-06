package cn.itcast.zt.producer.service;

import cn.itcast.zt.producer.domain.QMessage;

import java.util.List;

/**
 * 消息处理
 * Created by zhangtian on 2017/6/1.
 */
public interface QMessageService {
    /**
     * 获取QMessage
     * @param messageId
     * @return
     */
    public QMessage getQMessage(String messageId) ;

    /**
     * 添加消息
     * @param qMessage
     * @return
     */
    public int addQMessage(QMessage qMessage) ;

    /**
     * 更新消息
     * @param qMessage
     * @return
     */
    public int updateQMessage(QMessage qMessage) ;

    /**
     * 删除消息
     * @param messageId
     * @return
     */
    public int deleteQMessage(String messageId) ;

    /**
     * 获取所有消息
     * @param currentTime
     * @return
     */
    public List<QMessage> selectAllQMessage(Long currentTime) ;
}

package cn.itcast.zt.producer.domain;
import java.io.Serializable;
/**
 * 消息
 * Created by zhangtian on 2017/6/1.
 */
public class QMessage implements Serializable {
    // 主键唯一识别码
    private Integer id ;
    // 消息ID
    private String messageId ;
    // 业务线标识
    private String businessMark ;
    // 消息内容
    private String messageContent ;
    // 消息状态
    private Integer status ;
    // 重试次数
    private Integer retry ;
    // 消息投递地址
    private String destination ;
    // 投递类型
    private int destType ;
    // 是否是事务消息
    private int transaction ;
    // 消息持久化
    private int persistent ;
    // n2识别
    private int n2 ;
    // 时间戳
    private long timeStamp ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDestType() {
        return destType;
    }

    public void setDestType(int destType) {
        this.destType = destType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getBusinessMark() {
        return businessMark;
    }

    public void setBusinessMark(String businessMark) {
        this.businessMark = businessMark;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public int getPersistent() {
        return persistent;
    }

    public void setPersistent(int persistent) {
        this.persistent = persistent;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

package cn.itcast.zt.consumer.domain;
import java.io.Serializable;

/**
 * Created by zhangtian on 2017/6/2.
 */
public class N1Record implements Serializable {
    // 主键唯一ID
    private Integer id ;
    // 消息ID
    private String messageId ;
    // 消息时间戳
    private Long timeStamp ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}

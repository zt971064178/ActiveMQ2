package cn.itcast.zt.consumer.domain;
import java.io.Serializable;

/**
 * Created by zhangtian on 2017/6/2.
 */
public class N2Record implements Serializable{
    // 主键唯一ID
    private Integer id ;
    // 业务线标识
    private String businessMark ;
    // 消息时间戳
    private Long timeStamp ;
    // 队列名称
    private String destName ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessMark() {
        return businessMark;
    }

    public void setBusinessMark(String businessMark) {
        this.businessMark = businessMark;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }
}

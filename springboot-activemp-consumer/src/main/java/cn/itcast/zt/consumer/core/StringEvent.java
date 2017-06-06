package cn.itcast.zt.consumer.core;

/**
 * Created by zhangtian on 2017/6/2.
 */
public class StringEvent implements Event<String> {
    private String messageId;// 消息ID
    private String topic;// 消息地址
    private String content;// 消息内容

    public StringEvent() {

    }

    public StringEvent(String messageId, String topic, String data) {
        this.messageId = messageId;
        this.topic = topic;
        this.content = data;
    }

    @Override
    public String messageId() {
        return messageId;
    }

    @Override
    public String topic() {
        return topic;
    }

    @Override
    public String content() {
        return content;
    }
}

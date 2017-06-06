package cn.itcast.zt.consumer.core;

/**
 * Created by zhangtian on 2017/6/2.
 */
public interface Event<T> {
    // 消息ID
    String messageId() ;

    // 消息地址
    String topic() ;

    // 消息内容
    T content() ;
}

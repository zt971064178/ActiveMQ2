package cn.itcast.zt.producer.core;

/**
 * Created by zhangtian on 2017/6/1.
 */
public interface MessageCallback {
    // 事务消息处理成功
    void onSuccess(String messageId) ;

    // 事务消息处理失败
    void onFail(Exception e, String messageId) ;
}

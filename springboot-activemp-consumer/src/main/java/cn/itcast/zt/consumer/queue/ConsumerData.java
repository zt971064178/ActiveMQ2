package cn.itcast.zt.consumer.queue;

import cn.itcast.zt.consumer.annotations.Consumer;
import cn.itcast.zt.consumer.annotations.Listener;
import cn.itcast.zt.consumer.core.Event;

/**
 * Created by zhangtian on 2017/6/6.
 */
@Consumer
public class ConsumerData {
    @Listener(topic = "zhangtian.queue", transaction = false, n2 = true)
    public String getMessage(Event event) {
        System.out.println(event.messageId());
        System.out.println(event.topic());
        System.out.println(event.content());
        return "SUCCESS..." ;
    }
}

package cn.itcast.zt.consumer.core;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by zhangtian on 2017/6/2.
 */
public class MessageMethodHandle implements MessageListener {
    private static final Logger log = LoggerFactory.getLogger(MessageMethodHandle.class);
    //@Consumer注解的对象
    private Object bean;
    //@Listener注解的方法
    private Method method;
    //broker连接session
    private Session session;
    //消息投递地址
    private String destination;
    //消费消息的线程池
    private Executor executor;
    //是否支持事务
    private boolean transaction;
    //是否是n2级别的消息
    private boolean n2;
    //消息幂等处理
    private RepeatMessageHandle repeatMessageHandle;

    public MessageMethodHandle() {

    }

    public Object getBean() {
        return bean;
    }

    public MessageMethodHandle setBean(Object bean) {
        this.bean = bean;
        return this ;
    }

    public Method getMethod() {
        return method;
    }

    public MessageMethodHandle setMethod(Method method) {
        this.method = method;
        return this ;
    }

    public Session getSession() {
        return session;
    }

    public MessageMethodHandle setSession(Session session) {
        this.session = session;
        return this ;
    }

    public String getDestination() {
        return destination;
    }

    public MessageMethodHandle setDestination(String destination) {
        this.destination = destination;
        return this ;
    }

    public Executor getExecutor() {
        return executor;
    }

    public MessageMethodHandle setExecutor(Executor executor) {
        this.executor = executor;
        return this ;
    }

    public boolean isTransaction() {
        return transaction;
    }

    public MessageMethodHandle setTransaction(boolean transaction) {
        this.transaction = transaction;
        return this ;
    }

    public boolean isN2() {
        return n2;
    }

    public MessageMethodHandle setN2(boolean n2) {
        this.n2 = n2;
        return this ;
    }

    public RepeatMessageHandle getRepeatMessageHandle() {
        return repeatMessageHandle;
    }

    public MessageMethodHandle setRepeatMessageHandle(RepeatMessageHandle repeatMessageHandle) {
        this.repeatMessageHandle = repeatMessageHandle;
        return this ;
    }

    /**
     * 消费消息
     * @param event
     * @throws Exception
     */
    public void invokeMethod(Event event) throws Exception {
        method.invoke(bean, event);
    }

    /**
     * 监听消息队列
     * @param message broker中的消息
     */
    @Override
    public void onMessage(Message message) {
        final MapMessage mapMessage = (MapMessage) message;
        try {
            final String messageId = mapMessage.getString("messageId");
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        if (messageId != null) {
                            final Map<String, Object> map = Maps.newHashMap();
                            map.put("data", mapMessage.getString("data"));
                            map.put("messageId", mapMessage.getString("messageId"));
                            map.put("timeStamp", mapMessage.getString("timeStamp"));
                            //是否是n2级别
                            if (isN2()) {
                                map.put("topic", mapMessage.getString("topic"));
                                map.put("businessMark", mapMessage.getString("businessMark"));
                            }
                            repeatMessageHandle.repeatHandle(map, MessageMethodHandle.this, isN2());
                            //是否开启事务
                            if (transaction) {
                                session.commit();
                            } else {
                                message.acknowledge();
                            }
                        }
                    } catch (Exception e) {
                        log.error("handle message error:{}", e);
                    }
                }
            });
        }catch (Exception e) {
            log.error("handle message error: {}", e);
        }
    }
}

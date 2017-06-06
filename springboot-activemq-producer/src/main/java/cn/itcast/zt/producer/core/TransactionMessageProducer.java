package cn.itcast.zt.producer.core;

import cn.itcast.zt.producer.domain.QMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.jms.MessageProducer;

/**
 * 带有事务的消息处理
 * Created by zhangtian on 2017/6/1.
 */
@Component
public class TransactionMessageProducer implements BeanFactoryAware, SmartInitializingSingleton, Ordered {
    private static final Logger log = LoggerFactory.getLogger(TransactionMessageProducer.class);

    // bean工厂
    private BeanFactory beanFactory ;

    // activeMQ连接工厂
    private ActiveMQConnectionFactory activeMQConnectionFactory ;

    // 消息处理回调
    private MessageCallback messageCallback ;

    // broker连接
    private Connection connection ;

    public TransactionMessageProducer() {

    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
        return activeMQConnectionFactory;
    }

    public void setActiveMQConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        this.activeMQConnectionFactory = activeMQConnectionFactory;
    }

    public MessageCallback getMessageCallback() {
        return messageCallback;
    }

    public void setMessageCallback(MessageCallback messageCallback) {
        this.messageCallback = messageCallback;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory ;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.messageCallback = beanFactory.getBean(MessageCallback.class);
        this.activeMQConnectionFactory = beanFactory.getBean(ActiveMQConnectionFactory.class);
        try {
            this.connection = activeMQConnectionFactory.createConnection();
            connection.start();
        }catch (JMSException e) {
            throw  new RuntimeException("active broker connect connection error", e) ;
        }
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    /**
     * 发送消息至broker
     * @param qMessage
     */
    public void sendMessage(QMessage qMessage) {
        Session session = null ;

        try {
            session = connection.createSession(qMessage.getTransaction() != 0, ActiveMQSession.AUTO_ACKNOWLEDGE) ;
            // 创建队列
            Queue queue = session.createQueue(qMessage.getDestination()) ;
            // 创建消息发送者
            MessageProducer producer = session.createProducer(queue) ;
            producer.setDeliveryMode(qMessage.getPersistent() != 0 ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
            // 创建消息
            MapMessage message = session.createMapMessage() ;
            message.setString("messageId", qMessage.getMessageId());
            message.setString("data", qMessage.getMessageContent());
            message.setString("timeStamp", String.valueOf(qMessage.getTimeStamp()));
            message.setString("destination", String.valueOf(qMessage.getDestination()));

            // 如果消息是n2级别，创建业务标识
            if (qMessage.getN2() != 0) {
                if(StringUtils.isNotBlank(qMessage.getBusinessMark())){
                    message.setString("businessMark", qMessage.getBusinessMark());
                }else {
                    // 如果n2级别的消息，businessMark为空，抛出异常
                    throw new RuntimeException("n2 level message require businessMark not empty...");
                }
            }

            // 发送消息
            producer.send(message) ;
            // 如果是事务消息
            if(qMessage.getTransaction() != 0) {
                session.commit();
            }
            // 消息向broker发送成功
            messageCallback.onSuccess(qMessage.getMessageId());
        }catch (JMSException e){
            log.error("send message to broker error:{}", e);
            messageCallback.onFail(e, qMessage.getMessageId());
        }finally{
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    log.error("close session error:{}", e);
                }
            }
        }
    }
}

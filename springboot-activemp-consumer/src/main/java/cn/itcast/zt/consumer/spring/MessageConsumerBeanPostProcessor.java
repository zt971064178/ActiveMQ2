package cn.itcast.zt.consumer.spring;

import cn.itcast.zt.consumer.annotations.Consumer;
import cn.itcast.zt.consumer.annotations.Listener;
import cn.itcast.zt.consumer.core.Event;
import cn.itcast.zt.consumer.core.MessageMethodHandle;
import cn.itcast.zt.consumer.core.RepeatMessageHandle;
import com.google.common.collect.Lists;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by zhangtian on 2017/6/2.
 */
public class MessageConsumerBeanPostProcessor implements BeanPostProcessor, Ordered, BeanFactoryAware,SmartInitializingSingleton {

    private Object primitiveBean;
    //bean工厂
    private BeanFactory beanFactory;
    //ActiveMQ连接工厂
    private ActiveMQConnectionFactory activeMQConnectionFactory;
    //线程池
    private Executor executor;
    //消息幂等处理
    private RepeatMessageHandle repeatMessageHandle;
    //消费者集合
    private List<Object> consumerBeans = Lists.newArrayList();

    public MessageConsumerBeanPostProcessor() {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory ;
    }

    @Override
    public void afterSingletonsInstantiated() {
        activeMQConnectionFactory = beanFactory.getBean(ActiveMQConnectionFactory.class);
        executor = beanFactory.getBean(Executor.class);
        repeatMessageHandle = beanFactory.getBean(RepeatMessageHandle.class);
        try {
            for (Object consumerBean : consumerBeans) {
                //处理消费者监听器
                register(consumerBean);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 注册消息队列监听器
     *
     * @param bean
     */
    private void register(Object bean) throws Exception {
        Class<?> clazz = bean.getClass();
        //获取@Listener注解的方法
        for (Method method : clazz.getDeclaredMethods()) {
            Class<?>[] types = method.getParameterTypes();
            //方法参数个数必须为1，并且参数的类型为Event的子类
            if (types.length != 1 || !Event.class.isAssignableFrom(types[0])) {
                continue;
            }
            //获取消息处理方法
            Listener listener = method.getAnnotation(Listener.class);
            //创建连接
            Connection connection = activeMQConnectionFactory.createConnection();
            RedeliveryPolicy policy = ((ActiveMQConnection) connection).getRedeliveryPolicy();
            //设置重试策略
            policy.setInitialRedeliveryDelay(1000);
            policy.setBackOffMultiplier(2);
            policy.setUseExponentialBackOff(true);
            policy.setMaximumRedeliveries(2);
            //启动连接
            connection.start();
            //创建会话
            Session session = connection.createSession(listener.transaction(), ActiveMQSession.AUTO_ACKNOWLEDGE);
            //创建队列
            Destination queue = session.createQueue(listener.topic());
            //创建消费者
            MessageConsumer consumer = session.createConsumer(queue);
            //创建消息处理类
            MessageMethodHandle messageMethodHandle = new MessageMethodHandle()
                    .setBean(bean)
                    .setDestination(listener.topic())
                    .setExecutor(executor)
                    .setMethod(method)
                    .setN2(listener.n2())
                    .setSession(session)
                    .setTransaction(listener.transaction())
                    .setRepeatMessageHandle(repeatMessageHandle);
            //设置消息监听器
            consumer.setMessageListener(messageMethodHandle);
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        this.primitiveBean = bean ;
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //获取标注@Consumer的注解
        Consumer consumer = bean.getClass().getAnnotation(Consumer.class);
        //bean上注解@Consumer
        if (consumer != null) {
            if (AopUtils.isAopProxy(bean)) {
                consumerBeans.add(primitiveBean);
            } else {
                consumerBeans.add(bean);
            }
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}

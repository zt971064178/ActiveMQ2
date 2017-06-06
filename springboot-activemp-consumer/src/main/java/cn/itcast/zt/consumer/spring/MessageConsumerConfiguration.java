package cn.itcast.zt.consumer.spring;

import cn.itcast.zt.consumer.config.ConsumerConfig;
import cn.itcast.zt.consumer.core.RepeatMessageHandle;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhangtian on 2017/6/2.
 */
@Configuration
@ComponentScan("cn.itcast.zt.consumer")
public class MessageConsumerConfiguration {

    @Autowired(required = false)
    private ConsumerConfig consumerConfig ;

    /**
     * 创建消费者解析器
     *
     * @return
     */
    @Bean
    public MessageConsumerBeanPostProcessor messageConsumerBeanPostProcessor() {
        return new MessageConsumerBeanPostProcessor();
    }

    /**
     * 创建ActiveMQ连接工厂
     *
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory(consumerConfig.getUserName(), consumerConfig.getPassword(), consumerConfig.getBrokerUrl());
    }

    /**
     * 创建线程池
     *
     * @return
     */
    @Bean
    public Executor executor() {
        return Executors.newFixedThreadPool(consumerConfig.getPoolSize());
    }

    /**
     * 创建消息幂等处理
     *
     * @return
     */
    @Bean
    public RepeatMessageHandle repeatMessageHandle() {
        return new RepeatMessageHandle();
    }
}

package cn.itcast.zt.producer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者配置项
 * Created by zhangtian on 2017/6/1.
 */
@Configuration
@ComponentScan(value = "cn.itcast.zt.producer")
public class MessageProducerConfiguration {

    @Autowired
    private ProducerConfig producerConfig ;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory(producerConfig.getUserName(), producerConfig.getPassword(), producerConfig.getBrokerUrl()) ;
    }
}

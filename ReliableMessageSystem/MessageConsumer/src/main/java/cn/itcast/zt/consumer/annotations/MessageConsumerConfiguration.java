package cn.itcast.zt.consumer.annotations;

import cn.itcast.zt.consumer.config.ConsumerConfig;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhangtian on 2017/6/2.
 */
@Configuration
@ComponentScan(value = "cn.itcast.zt.consumer")
public class MessageConsumerConfiguration {
    @Autowired(required = false)
    private ConsumerConfig consumerConfig ;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory(consumerConfig.getUserName(), consumerConfig.getPassword(), consumerConfig.getBrokerUrl()) ;
    }
}

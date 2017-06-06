package cn.itcast.zt.producer.annotations;

import cn.itcast.zt.producer.config.MessageProducerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangtian on 2017/6/1.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MessageProducerConfiguration.class)
@EnableScheduling
public @interface EnableProducer {

}

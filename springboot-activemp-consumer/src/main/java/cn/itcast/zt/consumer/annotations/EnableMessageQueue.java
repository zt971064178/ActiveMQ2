package cn.itcast.zt.consumer.annotations;

import cn.itcast.zt.consumer.spring.MessageConsumerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangtian on 2017/6/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MessageConsumerConfiguration.class)
public @interface EnableMessageQueue {

}

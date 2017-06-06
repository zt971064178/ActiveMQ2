package cn.itcast.zt.consumer.annotations;

import java.lang.annotation.*;

/**
 * Created by zhangtian on 2017/6/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Listener {
    // 消息队列的名称
    String topic() default "";

    // 是否支持事务,默认不开启
    boolean transaction() default false;

    // 是否支持n2级别的消息，默认不开启
    boolean n2() default false;
}

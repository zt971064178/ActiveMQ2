package cn.itcast.zt.consumer.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by zhangtian on 2017/6/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface Consumer {
}

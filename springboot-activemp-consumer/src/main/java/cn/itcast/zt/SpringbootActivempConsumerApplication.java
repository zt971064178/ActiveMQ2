package cn.itcast.zt;

import cn.itcast.zt.consumer.annotations.EnableMessageQueue;
import cn.itcast.zt.consumer.config.ConsumerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableMessageQueue
public class SpringbootActivempConsumerApplication {

	@Bean
	public ConsumerConfig createConsumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig() ;
		consumerConfig.setBrokerUrl("tcp://127.0.0.1:61616");
		consumerConfig.setUserName("admin");
		consumerConfig.setPassword("admin");
		consumerConfig.setPoolSize(100);

		return consumerConfig ;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringbootActivempConsumerApplication.class, args);
	}
}

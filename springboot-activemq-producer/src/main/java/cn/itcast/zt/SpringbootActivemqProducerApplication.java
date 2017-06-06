package cn.itcast.zt;

import cn.itcast.zt.producer.annotations.EnableProducer;
import cn.itcast.zt.producer.config.ProducerConfig;
import cn.itcast.zt.producer.core.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableProducer
@EnableTransactionManagement
@RestController
public class SpringbootActivemqProducerApplication {

	@Autowired
	private MessageProducer producer ;

	@Bean
	public ProducerConfig createProducerConfig(){
		ProducerConfig producerConfig = new ProducerConfig() ;
		producerConfig.setBrokerUrl("tcp://127.0.0.1:61616");
		producerConfig.setUserName("admin");
		producerConfig.setPassword("admin");

		return producerConfig ;
	}



	public static void main(String[] args) {
		SpringApplication.run(SpringbootActivemqProducerApplication.class, args);
	}


	@GetMapping(value = "createMessage")
	public String createMessage() {
		producer.setDestName("zhangtian.queue");
		producer.setN2(false);
		producer.setTransaction(false);
		producer.setPersistent(false);

		Map<String, String> data = new HashMap<String, String>() ;
		data.put("message", "我爱北京天安门，天安门前是我家！") ;
		producer.sendMessage(data);

		return "SUCCESS..." ;
	}
}

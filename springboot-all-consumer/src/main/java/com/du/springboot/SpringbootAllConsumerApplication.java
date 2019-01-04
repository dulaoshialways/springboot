package com.du.springboot;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author djg
 */
@SpringBootApplication
@EnableDubboConfiguration
@ImportResource({ "classpath:dubbo-consumer.xml" })
public class SpringbootAllConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAllConsumerApplication.class, args);
	}
}

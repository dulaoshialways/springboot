package com.du.springboot;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author djg
 */
@SpringBootApplication
@EnableDubboConfiguration
public class SpringbootAllProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAllProviderApplication.class, args);
	}
}
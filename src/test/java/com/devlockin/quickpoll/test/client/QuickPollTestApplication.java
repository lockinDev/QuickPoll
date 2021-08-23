package com.devlockin.quickpoll.test.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(scanBasePackages = { "net", "com" } )
public class QuickPollTestApplication {

	private static final Logger LOG = LoggerFactory.getLogger(QuickPollTestApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(QuickPollTestApplication.class, args);
	}
	
}

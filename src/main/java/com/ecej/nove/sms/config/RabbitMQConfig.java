package com.ecej.nove.sms.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue mailQueue() {
		return new Queue("ecejmail");
	}

	@Bean
	public Queue smsQueue() {
		return new Queue("ecejsms");
	}

	@Bean
	public Queue smsFailQueue() {
		return new Queue("ecejsmsfail");
	}

	@Bean
	public Queue jpushQueue() {
		return new Queue("ecejjpush");
	}
}

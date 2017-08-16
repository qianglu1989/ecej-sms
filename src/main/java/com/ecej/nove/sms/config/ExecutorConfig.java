package com.ecej.nove.sms.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 配置异步任务分发
 * 
 * @author QIANG
 *
 */
@Configuration
@EnableScheduling
@EnableAsync
public class ExecutorConfig {

	@Value("${sms.executor.corePoolSize}")
	private int corePoolSize;

	@Value("${sms.executor.maxPoolSize}")
	private int maxPoolSize;

	@Value("${sms.executor.queueCapacity}")
	private int queueCapacity;



	@Bean(name = "mailAsync")
	public Executor mailAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setThreadNamePrefix("MailExecutor-");
		executor.initialize();
		return executor;
	}

	@Bean(name = "smsConsumer")
	public Executor smsConsumer() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setThreadNamePrefix("ConsumerExecutor-");
		executor.initialize();
		return executor;
	}

}

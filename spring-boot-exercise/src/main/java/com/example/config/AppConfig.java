package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AppConfig {

	@Bean("my-threadpool")
	ThreadPoolTaskExecutor myThreadPool() {
		var tpte = new ThreadPoolTaskExecutor();
		tpte.setThreadNamePrefix("mythread-");
		tpte.setCorePoolSize(8);
		tpte.setMaxPoolSize(64);
		tpte.setQueueCapacity(100);
		tpte.initialize();
		return tpte;
	}
	@Bean("my-threadpool2")
	VirtualThreadTaskExecutor myThreadPool2() {
		return new VirtualThreadTaskExecutor("myvirtualthread");
	}
}

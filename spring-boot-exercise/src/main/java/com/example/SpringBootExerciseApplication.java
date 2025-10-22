package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.AsyncTaskExecutor;

import com.example.service.SimpleBusinessService;

@SpringBootApplication
public class SpringBootExerciseApplication implements ApplicationRunner {

	private final SimpleBusinessService businessService;
	private final AsyncTaskExecutor threadPoolTaskExecutor;
	public SpringBootExerciseApplication(SimpleBusinessService businessService, 
			@Qualifier("my-threadpool2") AsyncTaskExecutor threadPoolTaskExecutor) {
		this.businessService = businessService;
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExerciseApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.err.println(businessService.getClass());
		// class com.example.service.SimpleBusinessService$$SpringCGLIB$$0
		businessService.asyncWorkHard().thenAcceptAsync(result -> {
			System.out.println("[%s] Received the result: %d".formatted(Thread.currentThread().getName(),result));
		},threadPoolTaskExecutor);
	}

}

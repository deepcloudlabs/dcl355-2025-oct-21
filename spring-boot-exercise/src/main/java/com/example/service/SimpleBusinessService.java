package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SimpleBusinessService {

	@Async("my-threadpool2")
	public CompletableFuture<Integer> asyncWorkHard() {
		System.err.println("[%s] asyncWorkHard() is running...".formatted(Thread.currentThread().getName()));
		try{TimeUnit.SECONDS.sleep(5);}catch (Exception e) {}
		return CompletableFuture.completedFuture(42);
	}
}

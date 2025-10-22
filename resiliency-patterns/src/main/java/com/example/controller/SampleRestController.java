package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class SampleRestController {

	@GetMapping(params= {"name"})
	//@RateLimiter(name="sampleRestController")
	//@Bulkhead(type = Type.SEMAPHORE, name = "sampleRestController")
	public String getMessage(@RequestParam String name) {
		return "[%s] Hello, %s!".formatted(Thread.currentThread().getName(),name);
	}
}

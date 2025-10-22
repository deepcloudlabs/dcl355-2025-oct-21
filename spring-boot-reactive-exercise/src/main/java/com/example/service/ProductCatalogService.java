package com.example.service;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.Product;

import reactor.core.publisher.Flux;

@Service
public class ProductCatalogService {
	private static final List<Product> PRODUCTS = List.of(new Product("1", "iphone17"),new Product("2", "s24"),new Product("3", "hp deskjet 7020"));

	public Flux<Product> findAll() {
		return Flux.fromIterable(PRODUCTS).delayElements(Duration.ofSeconds(1));
	}

}

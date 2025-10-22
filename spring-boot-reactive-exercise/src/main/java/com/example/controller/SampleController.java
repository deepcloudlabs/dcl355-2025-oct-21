package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Product;
import com.example.service.ProductCatalogService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/products")
public class SampleController {

	private final ProductCatalogService productCatalogService;

	public SampleController(ProductCatalogService productCatalogService) {
		this.productCatalogService = productCatalogService;
	}

	@GetMapping
	public Flux<Product> getAllProducts(){
		return productCatalogService.findAll();
	}
}

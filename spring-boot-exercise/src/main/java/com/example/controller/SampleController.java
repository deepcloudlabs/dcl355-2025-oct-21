package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Product;
import com.example.service.ProductCatalogService;

@RestController
@RequestMapping("/products")
public class SampleController {

	private final ProductCatalogService productService;

	public SampleController(ProductCatalogService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getAllProducts(){
		return productService.findAll();
	}
}

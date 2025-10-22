package com.example.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.dto.Product;

@Service
public class ProductCatalogService {

	private static final List<Product> PRODUCTS = List.of(new Product("1", "iphone17"),new Product("2", "s24"),new Product("3", "hp deskjet 7020"));

	public List<Product> findAll() {
		try {TimeUnit.SECONDS.sleep(3);}catch (Exception e) {}
		return PRODUCTS;
	}

}

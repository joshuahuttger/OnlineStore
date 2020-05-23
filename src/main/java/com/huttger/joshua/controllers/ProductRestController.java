package com.huttger.joshua.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huttger.joshua.models.Product;
import com.huttger.joshua.models.ProductRepository;

@RestController
@RequestMapping(path="/products")
public class ProductRestController {
	@Autowired
	private ProductRepository productRepo;
	@GetMapping(path="/{id}")
	public Product getProductById(@PathVariable int id) {
		return productRepo.findById(id);
	}
	@GetMapping
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	@PostMapping
	public Product addProduct(@RequestBody Product newProduct) {
		return productRepo.save(newProduct);
	}
}

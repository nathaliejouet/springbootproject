package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Product;
import com.springboot.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> productList = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(productList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> selectProductById(@PathVariable("id") int id) {
		Product product = productService.selectById(id);
		return new ResponseEntity<Product>(product, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);
		return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestParam String nom, @RequestParam int prix) {
		productService.updateProduct(id, nom, prix);
		return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.OK);
	}
}

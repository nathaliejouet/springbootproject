package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.model.Product;
import com.springboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		if (productList.size() > 0) {
			return productList;
		} else {
			return new ArrayList<Product>();
		}
	}

	public Product selectById(int id) {
		Product productToFind = productRepository.findById(id).get();
		if (productToFind != null) {
			return productToFind;
		} else {
			return null;
		}
	}

	public ResponseEntity<Object> createProduct(Product model) {
		Product product = new Product();
		if (productRepository.findById(model.getId()).isPresent()) {
			return ResponseEntity.badRequest().body("The Product is already Present, Failed to Create new Product");
		} else {
			product.setNom(model.getNom());
			product.setPrix(model.getPrix());
			product.setCategories(model.getCategories());
			Product savedProduct = productRepository.save(product);
			if (productRepository.findById(savedProduct.getId()).isPresent()) {
				return ResponseEntity.ok("Product Created Successfully");
			} else {
				return ResponseEntity.unprocessableEntity().body("Failed Creating Product");
			}
		}

	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}

	public Product updateProduct(int id, String nom, int prix) {
		Product productToFind = productRepository.findById(id).get();
		if (productToFind != null) {
			if (nom != null) {
				productToFind.setNom(nom);
			}
			if (prix != 0) {
				productToFind.setPrix(prix);
			}

			productRepository.save(productToFind);
			return productToFind;
		} else {
			return null;
		}
	}

}

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

import com.springboot.model.Category;
import com.springboot.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categoryList = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(categoryList, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> selectCategoryById(@PathVariable("id") int id) {
		Category category = categoryService.selectById(id);
		return new ResponseEntity<Category>(category, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/category")
	public ResponseEntity<Object> createCategory(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") int id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<Category>(new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/category/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestParam String nom) {
		categoryService.updateCategory(id, nom);
		return new ResponseEntity<Category>(new HttpHeaders(), HttpStatus.OK);
	}
}

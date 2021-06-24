package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Category;
import com.springboot.model.Product;
import com.springboot.repository.CategoryRepository;
import com.springboot.repository.ProductRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	
	public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

	public List<Category> getAllCategories() {
		List<Category> categoryList = categoryRepository.findAll();
		if (categoryList.size() > 0) {
			return categoryList;
		} else {
			return new ArrayList<Category>();
		}
	}

	public Category selectById(int id) {
		Category categoryToFind = categoryRepository.findById(id).get();
		if (categoryToFind != null) {
			return categoryToFind;
		} else {
			return null;
		}
	}

	@Transactional
	public ResponseEntity<Object> createCategory(Category model) {
		Category newCategory = new Category();
		newCategory.setNom(model.getNom());
		List<Category> categoryList = new ArrayList<>();
		categoryList.add(newCategory);
		for (int i = 0; i < model.getProduits().size(); i++) {
			System.out.println("OK");
			if (!productRepository.findById(model.getProduits().get(i).getId()).isPresent()) {
				Product newProduct = model.getProduits().get(i);
				newProduct.setCategories(categoryList);
				Product savedProduct = productRepository.save(newProduct);
				if (!productRepository.findById(savedProduct.getId()).isPresent()) {
					return ResponseEntity.unprocessableEntity().body("Category Creation Failed");
				} else {
					return ResponseEntity.unprocessableEntity().body("Product is already Present");
				}
			}
		}
		return ResponseEntity.ok("Successfully created Category");
	}

	public void deleteCategory(int id) {
		categoryRepository.deleteById(id);
	}

	public Category updateCategory(int id, String nom) {
		Category categoryToFind = categoryRepository.findById(id).get();
		if (categoryToFind != null) {
			if (nom != null) {
				categoryToFind.setNom(nom);
			}

			categoryRepository.save(categoryToFind);
			return categoryToFind;
		} else {
			return null;
		}
	}

}

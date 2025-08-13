package com.BillingApp.serviceImpl;

import org.springframework.stereotype.Service;

import com.BillingApp.model.Category;
import com.BillingApp.repository.CategoryRepository;
import com.BillingApp.services.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
 
 @Override
 public List<Category> getAllCategories() {
     return categoryRepository.findAll();
 }

 @Override
 public Category createCategory(Category category) {
     if (categoryRepository.existsByName(category.getName())) {
         throw new RuntimeException("Category already exists!");
     }
     return categoryRepository.save(category);
 }

}

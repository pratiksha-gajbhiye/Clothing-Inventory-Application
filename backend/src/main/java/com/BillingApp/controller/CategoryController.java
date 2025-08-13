package com.BillingApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApp.model.Category;
import com.BillingApp.services.CategoryService;

import lombok.RequiredArgsConstructor;

//CategoryController.java
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
 private final CategoryService service;

 
 public CategoryController(CategoryService service) {
	this.service = service;
}

@GetMapping
 public List<Category> getAll() {
     return service.getAllCategories();
 }

 @PostMapping
 public Category create(@RequestBody Category category) {
     return service.createCategory(category);
 }
}

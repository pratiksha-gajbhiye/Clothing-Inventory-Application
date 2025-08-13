package com.BillingApp.services;

//src/main/java/com/billingapp/service/CategoryService.java


import java.util.List;
import com.BillingApp.model.Category;

public interface CategoryService {
    List<Category> getAllCategories();         // OR use just getAll()
    Category createCategory(Category category); // OR use just create()
}

package com.BillingApp.repository;

//src/main/java/com/billingapp/repository/CategoryRepository.java


import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
 boolean existsByName(String name);
}

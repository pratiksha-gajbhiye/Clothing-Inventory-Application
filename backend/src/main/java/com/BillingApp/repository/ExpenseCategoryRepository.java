package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.ExpenseCategory;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
}

package com.BillingApp.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.BillingApp.DTO.ExpenseDTO;
import com.BillingApp.model.Expense;
import com.BillingApp.model.ExpenseCategory;

public interface ExpenseService {
    Expense createExpense(ExpenseDTO dto);
    List<Expense> getAllExpenses();
    List<Expense> getExpensesBetween(LocalDate from, LocalDate to);

    ExpenseCategory createCategory(String name);
    List<ExpenseCategory> getAllCategories();
    
    Map<String, BigDecimal> getMonthlyExpenseSummary();
    Map<String, BigDecimal> getWeeklyExpenseSummary();

}
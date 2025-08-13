package com.BillingApp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillingApp.DTO.ExpenseDTO;
import com.BillingApp.model.Expense;
import com.BillingApp.model.ExpenseCategory;
import com.BillingApp.repository.ExpenseCategoryRepository;
import com.BillingApp.repository.ExpenseRepository;
import com.BillingApp.services.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired private ExpenseRepository expenseRepo;
    @Autowired private ExpenseCategoryRepository categoryRepo;

    @Override
    public Expense createExpense(ExpenseDTO dto) {
        ExpenseCategory category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Expense expense = new Expense();
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setCategory(category);

        return expenseRepo.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    @Override
    public List<Expense> getExpensesBetween(LocalDate from, LocalDate to) {
        return expenseRepo.findByDateBetween(from, to);
    }

    @Override
    public ExpenseCategory createCategory(String name) {
        ExpenseCategory category = new ExpenseCategory();
        category.setName(name);
        return categoryRepo.save(category);
    }

    @Override
    public List<ExpenseCategory> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Map<String, BigDecimal> getMonthlyExpenseSummary() {
        List<Expense> expenses = expenseRepo.findAll();
        return expenses.stream()
                .collect(Collectors.groupingBy(
                        exp -> exp.getDate().getMonth().name(), // "JANUARY", "FEBRUARY", etc.
                        Collectors.mapping(Expense::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));
    }

    @Override
    public Map<String, BigDecimal> getWeeklyExpenseSummary() {
        List<Expense> expenses = expenseRepo.findAll();
        return expenses.stream()
                .collect(Collectors.groupingBy(
                        exp -> "Week " + exp.getDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR),
                        Collectors.mapping(Expense::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));
    }
}

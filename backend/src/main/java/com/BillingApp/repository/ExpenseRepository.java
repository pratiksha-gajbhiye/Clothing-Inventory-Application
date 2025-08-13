package com.BillingApp.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BillingApp.model.Expense;
import com.BillingApp.model.Sale;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.date BETWEEN :from AND :to")
    BigDecimal sumTotalExpenseBetweenDates(@Param("from") LocalDate from, @Param("to") LocalDate to);

    // ✅ Correct method — field is `date`
    List<Expense> findByDateBetween(LocalDate from, LocalDate to);

    List<Expense> findByDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE DATE(e.date) = CURRENT_DATE")
    BigDecimal getTodayExpenseAmount();
    // ❌ REMOVE THIS — invalid!
    // List<Expense> findByExpenseDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

	long getTotalExpenseAmount();

	@Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
	Double getTotalExpensesAmount();


}

package com.BillingApp.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApp.DTO.ExpenseDTO;
import com.BillingApp.model.Expense;
import com.BillingApp.model.ExpenseCategory;
import com.BillingApp.services.ExpenseService;
import com.BillingApp.util.ExcelExportUtil;
import com.BillingApp.util.PdfGenerator;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseDTO dto) {
        return ResponseEntity.ok(expenseService.createExpense(dto));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Expense>> filterByDate(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(expenseService.getExpensesBetween(from, to));
    }

    

    @GetMapping("/category")
    public ResponseEntity<List<ExpenseCategory>> getAllCategories() {
        return ResponseEntity.ok(expenseService.getAllCategories());
    }
    
    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportToExcel() throws IOException {
        List<Expense> expenses = expenseService.getAllExpenses();
        ByteArrayInputStream in = ExcelExportUtil.exportExpensesToExcel(expenses);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=expenses.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<InputStreamResource> exportToPDF() {
        List<Expense> expenses = expenseService.getAllExpenses();
        ByteArrayInputStream in = PdfGenerator.exportExpensesToPDF(expenses);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=expenses.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }
    
    @GetMapping("/trends/monthly")
    public ResponseEntity<Map<String, BigDecimal>> getMonthlySummary() {
        return ResponseEntity.ok(expenseService.getMonthlyExpenseSummary());
    }

    @GetMapping("/trends/weekly")
    public ResponseEntity<Map<String, BigDecimal>> getWeeklySummary() {
        return ResponseEntity.ok(expenseService.getWeeklyExpenseSummary());
    }


}

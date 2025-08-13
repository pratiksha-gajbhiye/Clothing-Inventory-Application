package com.BillingApp.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private Long categoryId;
	
    
    public ExpenseDTO() {
		super();
	}


	public ExpenseDTO(String description, BigDecimal amount, LocalDate date, Long categoryId) {
		super();
		this.description = description;
		this.amount = amount;
		this.date = date;
		this.categoryId = categoryId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}



}

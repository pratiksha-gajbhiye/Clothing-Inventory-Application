package com.BillingApp.DTO;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CashBankEntryDTO {
    private Long id;
    private LocalDate date;
    private String accountType; // CASH or BANK
    private BigDecimal amount;
    private String description;
	
    public CashBankEntryDTO() {
		super();
	}

	public CashBankEntryDTO(Long id, LocalDate date, String accountType, BigDecimal amount, String description) {
		super();
		this.id = id;
		this.date = date;
		this.accountType = accountType;
		this.amount = amount;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

 

}

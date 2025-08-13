package com.BillingApp.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
@Data

public class LedgerEntryDTO {
	
    private LocalDate date;
    private String type;         // IN / OUT
    private String paymentMode;  // Cash, UPI, Bank, etc.
    private String note;
    private BigDecimal credit;   // if type = IN
    private BigDecimal debit;    // if type = OUT
    private BigDecimal balance;
	
    
    public LedgerEntryDTO() {
		super();
	}


	public LedgerEntryDTO(LocalDate date, String type, String paymentMode, String note, BigDecimal credit,
			BigDecimal debit, BigDecimal balance) {
		super();
		this.date = date;
		this.type = type;
		this.paymentMode = paymentMode;
		this.note = note;
		this.credit = credit;
		this.debit = debit;
		this.balance = balance;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public BigDecimal getCredit() {
		return credit;
	}


	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}


	public BigDecimal getDebit() {
		return debit;
	}


	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}



}
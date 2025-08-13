package com.BillingApp.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesReturnReportDTO {
    private String invoiceNo;
    private String customerName;
    private LocalDate returnDate;
    private Long id;
    private LocalDate date;
    private BigDecimal amount;
	
    public SalesReturnReportDTO() {
		super();
	}

	public SalesReturnReportDTO(String invoiceNo, String customerName, LocalDate returnDate, BigDecimal amount) {
		super();
		this.invoiceNo = invoiceNo;
		this.customerName = customerName;
		this.returnDate = returnDate;
		this.amount = amount;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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




}

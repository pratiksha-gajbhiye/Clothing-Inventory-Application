package com.BillingApp.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentDTO {
    private String type;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String paymentMode;
    private String note;
    private Long customerId;
    private Long supplierId;
	
    public PaymentDTO() {
		super();
	}

	public PaymentDTO(String type, LocalDate paymentDate, BigDecimal amount, String paymentMode, String note,
			Long customerId, Long supplierId) {
		super();
		this.type = type;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMode = paymentMode;
		this.note = note;
		this.customerId = customerId;
		this.supplierId = supplierId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}



}

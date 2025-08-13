package com.BillingApp.DTO;

//dto/PurchaseDTO.java


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Data
public class PurchaseDTO {
  private LocalDate purchaseDate;
  private String invoiceNumber;
  private String voucherNumber;

  private BigDecimal paidAmount;
  private BigDecimal dueAmount;
  private BigDecimal totalAmount;

  private BigDecimal discountAmount;
  private BigDecimal gstAmount;
  private BigDecimal roundOffAmount;

  private String paymentType;
  private String status;
  private String remarks;

  private Long supplierId;

  private List<PurchaseItemDTO> items;

  // Multipart file for invoice
  private MultipartFile invoiceFile;

  public PurchaseDTO() {
  }

public PurchaseDTO(LocalDate purchaseDate, String invoiceNumber, String voucherNumber, BigDecimal paidAmount,
		BigDecimal dueAmount, BigDecimal totalAmount, BigDecimal discountAmount, BigDecimal gstAmount,
		BigDecimal roundOffAmount, String paymentType, String status, String remarks, Long supplierId,
		List<PurchaseItemDTO> items, MultipartFile invoiceFile) {
	super();
	this.purchaseDate = purchaseDate;
	this.invoiceNumber = invoiceNumber;
	this.voucherNumber = voucherNumber;
	this.paidAmount = paidAmount;
	this.dueAmount = dueAmount;
	this.totalAmount = totalAmount;
	this.discountAmount = discountAmount;
	this.gstAmount = gstAmount;
	this.roundOffAmount = roundOffAmount;
	this.paymentType = paymentType;
	this.status = status;
	this.remarks = remarks;
	this.supplierId = supplierId;
	this.items = items;
	this.invoiceFile = invoiceFile;
}

public LocalDate getPurchaseDate() {
	return purchaseDate;
}

public void setPurchaseDate(LocalDate purchaseDate) {
	this.purchaseDate = purchaseDate;
}

public String getInvoiceNumber() {
	return invoiceNumber;
}

public void setInvoiceNumber(String invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
}

public String getVoucherNumber() {
	return voucherNumber;
}

public void setVoucherNumber(String voucherNumber) {
	this.voucherNumber = voucherNumber;
}

public BigDecimal getPaidAmount() {
	return paidAmount;
}

public void setPaidAmount(BigDecimal paidAmount) {
	this.paidAmount = paidAmount;
}

public BigDecimal getDueAmount() {
	return dueAmount;
}

public void setDueAmount(BigDecimal dueAmount) {
	this.dueAmount = dueAmount;
}

public BigDecimal getTotalAmount() {
	return totalAmount;
}

public void setTotalAmount(BigDecimal totalAmount) {
	this.totalAmount = totalAmount;
}

public BigDecimal getDiscountAmount() {
	return discountAmount;
}

public void setDiscountAmount(BigDecimal discountAmount) {
	this.discountAmount = discountAmount;
}

public BigDecimal getGstAmount() {
	return gstAmount;
}

public void setGstAmount(BigDecimal gstAmount) {
	this.gstAmount = gstAmount;
}

public BigDecimal getRoundOffAmount() {
	return roundOffAmount;
}

public void setRoundOffAmount(BigDecimal roundOffAmount) {
	this.roundOffAmount = roundOffAmount;
}

public String getPaymentType() {
	return paymentType;
}

public void setPaymentType(String paymentType) {
	this.paymentType = paymentType;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getRemarks() {
	return remarks;
}

public void setRemarks(String remarks) {
	this.remarks = remarks;
}

public Long getSupplierId() {
	return supplierId;
}

public void setSupplierId(Long supplierId) {
	this.supplierId = supplierId;
}

public List<PurchaseItemDTO> getItems() {
	return items;
}

public void setItems(List<PurchaseItemDTO> items) {
	this.items = items;
}

public MultipartFile getInvoiceFile() {
	return invoiceFile;
}

public void setInvoiceFile(MultipartFile invoiceFile) {
	this.invoiceFile = invoiceFile;
}
  
  
  
  
}


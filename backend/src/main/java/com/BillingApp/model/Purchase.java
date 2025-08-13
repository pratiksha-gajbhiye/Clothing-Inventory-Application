package com.BillingApp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.BillingApp.enum1.PurchaseStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Purchase {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate purchaseDate;
  private String invoiceNumber;
  private String voucherNumber;

  private String invoicePhoto; // Save file path here

  private BigDecimal paidAmount;
  private BigDecimal dueAmount;
  private BigDecimal totalAmount;

  private BigDecimal discountAmount;
  private BigDecimal gstAmount;
  private BigDecimal roundOffAmount;

  private String paymentType; // Cash, Card, Credit, UPI etc.

  @Enumerated(EnumType.STRING)
  private PurchaseStatus status; // PENDING, COMPLETED, CANCELLED

  private String remarks;

  @ManyToOne
  @JoinColumn(name = "supplier_id")
  private Supplier supplier;

  @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PurchaseItem> items;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted = false; // default to false

  // Audit
  private String createdBy;
  private String updatedBy;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  
  public Purchase() 
  {
  
  }


public Purchase(Long id, LocalDate purchaseDate, String invoiceNumber, String voucherNumber, String invoicePhoto,
		BigDecimal paidAmount, BigDecimal dueAmount, BigDecimal totalAmount, BigDecimal discountAmount,
		BigDecimal gstAmount, BigDecimal roundOffAmount, String paymentType, PurchaseStatus status, String remarks,
		Supplier supplier, List<PurchaseItem> items, String createdBy, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
	super();
	this.id = id;
	this.purchaseDate = purchaseDate;
	this.invoiceNumber = invoiceNumber;
	this.voucherNumber = voucherNumber;
	this.invoicePhoto = invoicePhoto;
	this.paidAmount = paidAmount;
	this.dueAmount = dueAmount;
	this.totalAmount = totalAmount;
	this.discountAmount = discountAmount;
	this.gstAmount = gstAmount;
	this.roundOffAmount = roundOffAmount;
	this.paymentType = paymentType;
	this.status = status;
	this.remarks = remarks;
	this.supplier = supplier;
	this.items = items;
	this.createdBy = createdBy;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
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


public String getInvoicePhoto() {
	return invoicePhoto;
}


public void setInvoicePhoto(String invoicePhoto) {
	this.invoicePhoto = invoicePhoto;
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


public PurchaseStatus getStatus() {
	return status;
}


public void setStatus(PurchaseStatus status) {
	this.status = status;
}


public String getRemarks() {
	return remarks;
}


public void setRemarks(String remarks) {
	this.remarks = remarks;
}


public Supplier getSupplier() {
	return supplier;
}


public void setSupplier(Supplier supplier) {
	this.supplier = supplier;
}


public List<PurchaseItem> getItems() {
	return items;
}


public void setItems(List<PurchaseItem> items) {
	this.items = items;
}


public String getCreatedBy() {
	return createdBy;
}


public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}


public LocalDateTime getCreatedAt() {
	return createdAt;
}


public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}


public LocalDateTime getUpdatedAt() {
	return updatedAt;
}


public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
}


public String getUpdatedBy() {
	return updatedBy;
}


public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}


public boolean isDeleted() {
	return isDeleted;
}


public void setDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
}
  
  
  
}

    


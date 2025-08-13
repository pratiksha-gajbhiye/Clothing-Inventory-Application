package com.BillingApp.model;

//model/PurchaseReturn.java

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.BillingApp.enum1.PaymentStatus;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PurchaseReturn {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String referenceNumber;
    private LocalDate returnDate;
    private String returnReason;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private BigDecimal totalAmount;
    private BigDecimal tax;
    private BigDecimal netAmount;

    private String attachmentPath; // file path or URL

    @ManyToOne
    private Supplier supplier;

    @OneToMany(mappedBy = "purchaseReturn", cascade = CascadeType.ALL)
    private List<PurchaseReturnItem> items;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	
    public PurchaseReturn() {
	}

	
    public PurchaseReturn(Long id, String referenceNumber, LocalDate returnDate, String returnReason,
			PaymentStatus paymentStatus, BigDecimal totalAmount, BigDecimal tax, BigDecimal netAmount,
			String attachmentPath, Supplier supplier, List<PurchaseReturnItem> items, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.referenceNumber = referenceNumber;
		this.returnDate = returnDate;
		this.returnReason = returnReason;
		this.paymentStatus = paymentStatus;
		this.totalAmount = totalAmount;
		this.tax = tax;
		this.netAmount = netAmount;
		this.attachmentPath = attachmentPath;
		this.supplier = supplier;
		this.items = items;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<PurchaseReturnItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseReturnItem> items) {
		this.items = items;
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
    
	
    
}

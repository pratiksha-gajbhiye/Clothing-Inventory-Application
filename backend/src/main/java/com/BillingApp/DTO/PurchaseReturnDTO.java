package com.BillingApp.DTO;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class PurchaseReturnDTO {
    private String referenceNumber;
    private LocalDate returnDate;
    private String returnReason;
    private String paymentStatus;
    private BigDecimal tax;
    private Long supplierId;
    private String attachmentPath;
    private List<PurchaseReturnItemDTO> items;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
   
    public PurchaseReturnDTO() 
    {
    	
	}

	public PurchaseReturnDTO(String referenceNumber, LocalDate returnDate, String returnReason, String paymentStatus,
			BigDecimal tax, Long supplierId, String attachmentPath, List<PurchaseReturnItemDTO> items) {
		super();
		this.referenceNumber = referenceNumber;
		this.returnDate = returnDate;
		this.returnReason = returnReason;
		this.paymentStatus = paymentStatus;
		this.tax = tax;
		this.supplierId = supplierId;
		this.attachmentPath = attachmentPath;
		this.items = items;
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

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public List<PurchaseReturnItemDTO> getItems() {
		return items;
	}

	public void setItems(List<PurchaseReturnItemDTO> items) {
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

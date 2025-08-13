package com.BillingApp.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {
    private String invoiceNumber;
    private LocalDateTime saleDate;
    private Long customerId;
    private double totalAmount;
    private List<SaleItemDTO> items;
	
    
    public SaleRequestDTO() {
		super();
	}


	public SaleRequestDTO(String invoiceNumber, LocalDateTime saleDate, Long customerId, double totalAmount,
			List<SaleItemDTO> items) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.saleDate = saleDate;
		this.customerId = customerId;
		this.totalAmount = totalAmount;
		this.items = items;
	}


	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public LocalDateTime getSaleDate() {
		return saleDate;
	}


	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public List<SaleItemDTO> getItems() {
		return items;
	}


	public void setItems(List<SaleItemDTO> items) {
		this.items = items;
	}



}

package com.BillingApp.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VariantDTO {
    private String size;
    private String color;
    private int quantity;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
	
    public VariantDTO() {
	}

	public VariantDTO(String size, String color, int quantity, BigDecimal purchasePrice, BigDecimal sellingPrice) {
		super();
		this.size = size;
		this.color = color;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}



}

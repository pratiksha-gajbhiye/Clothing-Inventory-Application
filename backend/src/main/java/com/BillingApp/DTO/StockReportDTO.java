package com.BillingApp.DTO;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockReportDTO {
    private String itemName;
    private int quantityAvailable;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
	
    public StockReportDTO() {
		super();
	}

	public StockReportDTO(String itemName, int quantityAvailable, BigDecimal purchasePrice, BigDecimal salePrice) {
		super();
		this.itemName = itemName;
		this.quantityAvailable = quantityAvailable;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}



}

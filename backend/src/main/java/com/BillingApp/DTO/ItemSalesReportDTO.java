package com.BillingApp.DTO;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemSalesReportDTO {
    private String itemName;
    private Long totalQuantitySold;
    private BigDecimal totalRevenue;
	
    
    public ItemSalesReportDTO() {
		super();
	}
	public ItemSalesReportDTO(String itemName, Long totalQuantitySold, BigDecimal totalRevenue) {
		this.itemName = itemName;
		this.totalQuantitySold = totalQuantitySold;
		this.totalRevenue = totalRevenue;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Long getTotalQuantitySold() {
		return totalQuantitySold;
	}
	public void setTotalQuantitySold(Long totalQuantitySold) {
		this.totalQuantitySold = totalQuantitySold;
	}
	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
}

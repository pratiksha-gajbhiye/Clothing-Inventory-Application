package com.BillingApp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarChartDTO {
    private String label;              // Example: "Jul 01", "Week 1", "2025-07", etc.
    private BigDecimal totalSales;     // Total sales amount for this label
    private BigDecimal totalPurchases; // Total purchases amount for this label
	
    public BarChartDTO() {
	}

	
	public BarChartDTO(String label, BigDecimal totalSales, BigDecimal totalPurchases) {
		super();
		this.label = label;
		this.totalSales = totalSales;
		this.totalPurchases = totalPurchases;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}

	public BigDecimal getTotalPurchases() {
		return totalPurchases;
	}

	public void setTotalPurchases(BigDecimal totalPurchases) {
		this.totalPurchases = totalPurchases;
	}

    
    

}

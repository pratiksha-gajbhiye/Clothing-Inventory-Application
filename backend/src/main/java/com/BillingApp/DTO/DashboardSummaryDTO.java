package com.BillingApp.DTO;

import java.math.BigDecimal;

//=== DashboardSummaryDTO.java ===

import java.util.List;

public class DashboardSummaryDTO {
 private long totalCustomers;
 private long totalPurchases;
 private long totalSales;
 private long totalReturns;
 private long lowStockItems;
 private BigDecimal totalExpenses;
 private long totalLogins; // Optional (Traffic)
 private long newCustomers;
 private double salesGrowthPercent;

 private List<ChartDataDTO> monthlyPurchaseData;
 private List<ChartDataDTO> salesTrendData;

 public DashboardSummaryDTO() {

 }

public DashboardSummaryDTO(long totalCustomers, long totalPurchases, long totalSales, long totalReturns,
		long lowStockItems, BigDecimal totalExpenses, long totalLogins, long newCustomers, double salesGrowthPercent,
		List<ChartDataDTO> monthlyPurchaseData, List<ChartDataDTO> salesTrendData) {
	super();
	this.totalCustomers = totalCustomers;
	this.totalPurchases = totalPurchases;
	this.totalSales = totalSales;
	this.totalReturns = totalReturns;
	this.lowStockItems = lowStockItems;
	this.totalExpenses = totalExpenses;
	this.totalLogins = totalLogins;
	this.newCustomers = newCustomers;
	this.salesGrowthPercent = salesGrowthPercent;
	this.monthlyPurchaseData = monthlyPurchaseData;
	this.salesTrendData = salesTrendData;
}

public long getTotalCustomers() {
	return totalCustomers;
}

public void setTotalCustomers(long totalCustomers) {
	this.totalCustomers = totalCustomers;
}

public long getTotalPurchases() {
	return totalPurchases;
}

public void setTotalPurchases(long totalPurchases) {
	this.totalPurchases = totalPurchases;
}

public long getTotalSales() {
	return totalSales;
}

public void setTotalSales(long totalSales) {
	this.totalSales = totalSales;
}

public long getTotalReturns() {
	return totalReturns;
}

public void setTotalReturns(long totalReturns) {
	this.totalReturns = totalReturns;
}

public long getLowStockItems() {
	return lowStockItems;
}

public void setLowStockItems(long lowStockItems) {
	this.lowStockItems = lowStockItems;
}

public BigDecimal getTotalExpenses() {
	return totalExpenses;
}

public void setTotalExpenses(BigDecimal BigDecimal) {
	this.totalExpenses = BigDecimal;
}

public long getTotalLogins() {
	return totalLogins;
}

public void setTotalLogins(long totalLogins) {
	this.totalLogins = totalLogins;
}

public long getNewCustomers() {
	return newCustomers;
}

public void setNewCustomers(long newCustomers) {
	this.newCustomers = newCustomers;
}

public double getSalesGrowthPercent() {
	return salesGrowthPercent;
}

public void setSalesGrowthPercent(double salesGrowthPercent) {
	this.salesGrowthPercent = salesGrowthPercent;
}

public List<ChartDataDTO> getMonthlyPurchaseData() {
	return monthlyPurchaseData;
}

public void setMonthlyPurchaseData(List<ChartDataDTO> monthlyPurchaseData) {
	this.monthlyPurchaseData = monthlyPurchaseData;
}

public List<ChartDataDTO> getSalesTrendData() {
	return salesTrendData;
}

public void setSalesTrendData(List<ChartDataDTO> salesTrendData) {
	this.salesTrendData = salesTrendData;
}


 
 
}

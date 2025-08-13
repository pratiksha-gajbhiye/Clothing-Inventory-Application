package com.BillingApp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDTO {
	private double totalSales;
	private double totalPurchases;
	private double totalExpenses;
	private double todaySales;
	private double todayPurchases;
	private double todayExpenses;
	private long expiredItems;
	private long lowStockItems;
	private long totalCustomers;
	private double totalReturnsAmount;

	public DashboardDTO() {
	}

	public DashboardDTO(double totalSales, double totalPurchases, double totalExpenses, double todaySales,
			double todayPurchases, double todayExpenses, long expiredItems, long lowStockItems, long totalCustomers) {
		this.totalSales = totalSales;
		this.totalPurchases = totalPurchases;
		this.totalExpenses = totalExpenses;
		this.todaySales = todaySales;
		this.todayPurchases = todayPurchases;
		this.todayExpenses = todayExpenses;
		this.expiredItems = expiredItems;
		this.lowStockItems = lowStockItems;
		this.totalCustomers = totalCustomers;
	}

	public double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}

	public double getTotalPurchases() {
		return totalPurchases;
	}

	public void setTotalPurchases(double totalPurchases) {
		this.totalPurchases = totalPurchases;
	}

	public double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public double getTodaySales() {
		return todaySales;
	}

	public void setTodaySales(double todaySales) {
		this.todaySales = todaySales;
	}

	public double getTodayPurchases() {
		return todayPurchases;
	}

	public void setTodayPurchases(double todayPurchases) {
		this.todayPurchases = todayPurchases;
	}

	public double getTodayExpenses() {
		return todayExpenses;
	}

	public void setTodayExpenses(double todayExpenses) {
		this.todayExpenses = todayExpenses;
	}

	public long getExpiredItems() {
		return expiredItems;
	}

	public void setExpiredItems(long expiredItems) {
		this.expiredItems = expiredItems;
	}

	public long getLowStockItems() {
		return lowStockItems;
	}

	public void setLowStockItems(long lowStockItems) {
		this.lowStockItems = lowStockItems;
	}

	public long getTotalCustomers() {
	    return totalCustomers;
	}

	public void setTotalCustomers(long totalCustomers) {
	    this.totalCustomers = totalCustomers;
	}

	public double getTotalReturnsAmount() {
		return totalReturnsAmount;
	}

	public void setTotalReturnsAmount(double totalReturnsAmount) {
		this.totalReturnsAmount = totalReturnsAmount;
	}


    
	
}
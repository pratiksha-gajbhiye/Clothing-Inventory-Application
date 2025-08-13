package com.BillingApp.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {
    private long customers;
    private long purchases;
    private double expenses;
    private long returns;
    private long items;
    private double cashBank;
    private double todaySales;
    private double todayPurchases;
    private double todayExpenses;
	private double setSalesGrowthPercent;
    private long setLowStockItems;
    
    public DashboardStatsDTO() {
		super();
	}
    
    
	public DashboardStatsDTO(long customers, long purchases, double expenses, long returns, long items, double cashBank,
			double todaySales, double todayPurchases, double todayExpenses) {
		super();
		this.customers = customers;
		this.purchases = purchases;
		this.expenses = expenses;
		this.returns = returns;
		this.items = items;
		this.cashBank = cashBank;
		this.todaySales = todaySales;
		this.todayPurchases = todayPurchases;
		this.todayExpenses = todayExpenses;
	}


	public long getCustomers() {
		return customers;
	}
	public void setCustomers(long customers) {
		this.customers = customers;
	}
	public long getPurchases() {
		return purchases;
	}
	public void setPurchases(long purchases) {
		this.purchases = purchases;
	}
	public double getExpenses() {
		return expenses;
	}
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	public long getReturns() {
		return returns;
	}
	public void setReturns(long returns) {
		this.returns = returns;
	}
	public long getItems() {
		return items;
	}
	public void setItems(long items) {
		this.items = items;
	}
	public double getCashBank() {
		return cashBank;
	}
	public void setCashBank(double cashBank) {
		this.cashBank = cashBank;
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


	public double getSetSalesGrowthPercent() {
		return setSalesGrowthPercent;
	}


	public void setSetSalesGrowthPercent(double setSalesGrowthPercent) {
		this.setSalesGrowthPercent = setSalesGrowthPercent;
	}


	public long getSetLowStockItems() {
		return setLowStockItems;
	}


	public void setSetLowStockItems(long setLowStockItems) {
		this.setLowStockItems = setLowStockItems;
	}






	


	
    

}

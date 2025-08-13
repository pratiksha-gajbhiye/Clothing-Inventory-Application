package com.BillingApp.DTO;

import lombok.*;

import java.math.BigDecimal;

public class ProfitLossReportDTO {
    private BigDecimal totalSales;
    private BigDecimal totalPurchase;
    private BigDecimal totalExpense;
    private BigDecimal profitOrLoss;
	
    
    public ProfitLossReportDTO() {
		super();
	}
	public ProfitLossReportDTO(BigDecimal totalSales, BigDecimal totalPurchase, BigDecimal totalExpense,
			BigDecimal profitOrLoss) {
		super();
		this.totalSales = totalSales;
		this.totalPurchase = totalPurchase;
		this.totalExpense = totalExpense;
		this.profitOrLoss = profitOrLoss;
	}
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
	public BigDecimal getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(BigDecimal totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public BigDecimal getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}
	public BigDecimal getProfitOrLoss() {
		return profitOrLoss;
	}
	public void setProfitOrLoss(BigDecimal profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}
	



}

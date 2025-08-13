package com.BillingApp.services;

import java.math.BigDecimal;
import java.util.List;

import com.BillingApp.DTO.BarChartDTO;
import com.BillingApp.DTO.DashboardDTO;
import com.BillingApp.DTO.DashboardStatsDTO;
import com.BillingApp.DTO.DashboardSummaryDTO;
import com.BillingApp.DTO.StockAlertDTO;

public interface DashboardService {
    DashboardDTO getDashboardData();
    DashboardStatsDTO getDashboardStats();
    List<BarChartDTO> getBarChartData();
    List<StockAlertDTO> getStockAlerts();
	BigDecimal getTodayTotalSales();
    DashboardSummaryDTO getDashboardSummary();	BigDecimal getTodayPurchaseAmount();

}

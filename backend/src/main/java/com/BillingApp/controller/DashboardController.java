package com.BillingApp.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApp.DTO.BarChartDTO;
import com.BillingApp.DTO.DashboardStatsDTO;
import com.BillingApp.DTO.DashboardSummaryDTO;
import com.BillingApp.DTO.StockAlertDTO;
import com.BillingApp.services.DashboardService;

//=== CONTROLLER: DashboardController.java ===

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDTO> getDashboardSummary() {
        return ResponseEntity.ok(dashboardService.getDashboardSummary());
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    @GetMapping("/bar-chart")
    public ResponseEntity<List<BarChartDTO>> getBarChartData() {
        return ResponseEntity.ok(dashboardService.getBarChartData());
    }

    @GetMapping("/stock-alerts")
    public ResponseEntity<List<StockAlertDTO>> getStockAlerts() {
        return ResponseEntity.ok(dashboardService.getStockAlerts());
    }

    @GetMapping("/today-sales")
    public ResponseEntity<BigDecimal> getTodaySales() {
        return ResponseEntity.ok(dashboardService.getTodayTotalSales());
    }

    @GetMapping("/today-purchase")
    public ResponseEntity<BigDecimal> getTodayPurchase() {
        return ResponseEntity.ok(dashboardService.getTodayPurchaseAmount());
    }
}

package com.BillingApp.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.BillingApp.DTO.ExpenseDTO;
import com.BillingApp.DTO.ItemSalesReportDTO;
import com.BillingApp.DTO.ProfitLossReportDTO;
import com.BillingApp.DTO.SalesReturnReportDTO;
import com.BillingApp.DTO.StockReportDTO;

public interface ReportService {
    ProfitLossReportDTO getProfitLossReport(LocalDate from, LocalDate to);
//    List<ItemSalesReportDTO> getItemWiseSales(LocalDate from, LocalDate to);
    List<ItemSalesReportDTO> getItemWisePurchase(LocalDate from, LocalDate to);
    List<StockReportDTO> getStockReport();
    List<ExpenseDTO> getExpenseReport(LocalDate from, LocalDate to);
    List<SalesReturnReportDTO> getSalesReturnReport(LocalDate from, LocalDate to);
	List<ItemSalesReportDTO> getItemWiseSales(LocalDate from, LocalDate to);
}

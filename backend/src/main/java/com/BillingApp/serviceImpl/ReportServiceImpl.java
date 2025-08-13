package com.BillingApp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillingApp.DTO.ExpenseDTO;
import com.BillingApp.DTO.ItemSalesReportDTO;
import com.BillingApp.DTO.ProfitLossReportDTO;
import com.BillingApp.DTO.SalesReturnReportDTO;
import com.BillingApp.DTO.StockReportDTO;
import com.BillingApp.model.Expense;
import com.BillingApp.model.Item;
import com.BillingApp.model.SalesReturn;
import com.BillingApp.repository.ExpenseRepository;
import com.BillingApp.repository.ItemRepository;
import com.BillingApp.repository.PurchaseRepository;
import com.BillingApp.repository.SaleRepository;
import com.BillingApp.repository.SalesReturnRepository;
import com.BillingApp.services.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired private SaleRepository saleRepo;
    @Autowired private PurchaseRepository purchaseRepo;
    @Autowired private ExpenseRepository expenseRepo;
    @Autowired private ItemRepository itemRepo;
    @Autowired private SalesReturnRepository salesReturnRepo;

    @Override
    public ProfitLossReportDTO getProfitLossReport(LocalDate from, LocalDate to) {
        LocalDateTime startOfDay = from.atStartOfDay();
        LocalDateTime endOfDay = to.atTime(LocalTime.MAX);

        BigDecimal totalSales = saleRepo.sumTotalAmountBetweenDates(startOfDay, endOfDay);
        BigDecimal totalPurchase = purchaseRepo.sumTotalAmountBetweenDates(from, to);
        BigDecimal totalExpense = expenseRepo.sumTotalExpenseBetweenDates(from, to);

        BigDecimal profit = totalSales.subtract(totalPurchase.add(totalExpense));

        ProfitLossReportDTO dto = new ProfitLossReportDTO();
        dto.setTotalSales(totalSales);
        dto.setTotalPurchase(totalPurchase);
        dto.setTotalExpense(totalExpense);
        dto.setProfitOrLoss(profit);

        return dto;
    }

    @Override
    public List<ItemSalesReportDTO> getItemWisePurchase(LocalDate from, LocalDate to) {
        return purchaseRepo.getItemWisePurchase(from, to);
    }

    @Override
    public List<StockReportDTO> getStockReport() {
        List<Item> items = itemRepo.findAll();
        List<StockReportDTO> reports = new ArrayList<>();

        for (Item item : items) {
            StockReportDTO dto = new StockReportDTO(
                item.getName(),
                item.getStock(),
                BigDecimal.valueOf(item.getCostPrice()),
                BigDecimal.valueOf(item.getSellingPrice())
            );
            reports.add(dto);
        }

        return reports;
    }

    @Override
    public List getExpenseReport(LocalDate from, LocalDate to) {
        return expenseRepo.findByDateBetween(from, to);
    }

    @Override
    public List<SalesReturnReportDTO> getSalesReturnReport(LocalDate from, LocalDate to) {
        List<SalesReturn> returns = salesReturnRepo.findByReturnDateBetween(
            from.atStartOfDay(),
            to.atTime(LocalTime.MAX)
        );

        List<SalesReturnReportDTO> dtos = new ArrayList<>();

        for (SalesReturn sr : returns) {
            SalesReturnReportDTO dto = new SalesReturnReportDTO();
            dto.setId(sr.getId());
            dto.setDate(sr.getReturnDate().toLocalDate());
            dto.setAmount(sr.getAmount());
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public List<ItemSalesReportDTO> getItemWiseSales(LocalDate from, LocalDate to) {
        LocalDateTime startOfDay = from.atStartOfDay();
        LocalDateTime endOfDay = to.atTime(LocalTime.MAX);

        return saleRepo.getItemWiseSales(startOfDay, endOfDay);
    }
}

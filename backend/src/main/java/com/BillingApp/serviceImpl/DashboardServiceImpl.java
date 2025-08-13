package com.BillingApp.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BillingApp.DTO.BarChartDTO;
import com.BillingApp.DTO.DashboardDTO;
import com.BillingApp.DTO.DashboardStatsDTO;
import com.BillingApp.DTO.DashboardSummaryDTO;
import com.BillingApp.DTO.StockAlertDTO;
import com.BillingApp.DTO.ChartDataDTO;
import com.BillingApp.repository.CustomerRepository;
import com.BillingApp.repository.ExpenseRepository;
import com.BillingApp.repository.ItemRepository;
import com.BillingApp.repository.PurchaseRepository;
import com.BillingApp.repository.PurchaseReturnRepository;
import com.BillingApp.repository.SaleRepository;
import com.BillingApp.services.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CustomerRepository customerRepo;
    private final SaleRepository salesRepo;
    private final PurchaseRepository purchaseRepo;
    private final PurchaseReturnRepository returnRepo;
    private final ExpenseRepository expenseRepo;
    private final ItemRepository itemRepo;

    
    
    public DashboardServiceImpl(CustomerRepository customerRepo, SaleRepository salesRepo,
			PurchaseRepository purchaseRepo, PurchaseReturnRepository returnRepo, ExpenseRepository expenseRepo,
			ItemRepository itemRepo) {
		super();
		this.customerRepo = customerRepo;
		this.salesRepo = salesRepo;
		this.purchaseRepo = purchaseRepo;
		this.returnRepo = returnRepo;
		this.expenseRepo = expenseRepo;
		this.itemRepo = itemRepo;
	}

	@Override
    public DashboardSummaryDTO getDashboardSummary() {
        DashboardSummaryDTO dto = new DashboardSummaryDTO();

        dto.setTotalCustomers(customerRepo.count());
        dto.setTotalSales(salesRepo.count());
        dto.setTotalPurchases(purchaseRepo.count());
        dto.setTotalReturns(returnRepo.count());
        dto.setTotalExpenses(expenseRepo.getTodayExpenseAmount());
        dto.setLowStockItems(itemRepo.countLowStockItems(5));
        dto.setNewCustomers(customerRepo.countByCreatedAtAfter(LocalDate.now().minusDays(30)));
        dto.setSalesGrowthPercent(salesRepo.calculateMonthlyGrowth());

        dto.setMonthlyPurchaseData(purchaseRepo.getMonthlyPurchaseStats()); // returns List<ChartDataDTO>
        dto.setSalesTrendData(salesRepo.getMonthlySalesStats()); // returns List<ChartDataDTO>

        return dto;
    }

	@Override
	public DashboardDTO getDashboardData() {
	    DashboardDTO dto = new DashboardDTO();
	    dto.setTotalCustomers(customerRepo.count());
	    dto.setTotalReturnsAmount(salesRepo.getTotalSalesAmount());
	    dto.setTotalPurchases(purchaseRepo.getTotalPurchaseAmount());
	    dto.setTotalReturnsAmount(returnRepo.getTotalReturnAmount());
	    dto.setTotalExpenses(expenseRepo.getTotalExpensesAmount());
	    return dto;
	}

    

    @Override
    public DashboardStatsDTO getDashboardStats() {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        stats.setCustomers(customerRepo.countByCreatedAtAfter(LocalDate.now().minusDays(30)));
        stats.setSetSalesGrowthPercent(salesRepo.calculateMonthlyGrowth());
        stats.setSetLowStockItems(itemRepo.countLowStockItems(5));
        return stats;
    }

    @Override
    public List<BarChartDTO> getBarChartData() {
        return purchaseRepo.getMonthlyPurchaseBarChart(); // You need this method in your repo
    }

    @Override
    public List<StockAlertDTO> getStockAlerts() {
        return itemRepo.countLowStockItems(5); // You need this method in your itemRepo
    }

    @Override
    public BigDecimal getTodayTotalSales() {
        return salesRepo.getTodayTotalSales(); // Assume salesRepo returns today’s total
    }

    @Override
    public BigDecimal getTodayPurchaseAmount() {
        return purchaseRepo.getTodayTotalPurchases(); // Assume purchaseRepo returns today’s total
    }
}

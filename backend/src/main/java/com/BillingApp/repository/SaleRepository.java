package com.BillingApp.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BillingApp.DTO.ChartDataDTO;
import com.BillingApp.DTO.ItemSalesReportDTO;
import com.BillingApp.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findBySaleDate(LocalDateTime date);

    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM Sale s WHERE s.saleDate BETWEEN :from AND :to")
    BigDecimal sumTotalAmountBetweenDates(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("SELECT new com.BillingApp.DTO.ItemSalesReportDTO(i.name, SUM(si.quantity), SUM(si.subtotal)) " +
           "FROM SaleItem si JOIN si.item i JOIN si.sale s " +
           "WHERE s.saleDate BETWEEN :from AND :to GROUP BY i.name")
    List<ItemSalesReportDTO> getItemWiseSales(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    Stream<Sale> findBySaleDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query(value = "SELECT COALESCE(SUM(total_amount), 0) FROM sale WHERE DATE(date) = CURRENT_DATE", nativeQuery = true)
    BigDecimal getTodaySalesAmount();

	double calculateMonthlyGrowth();

	List<ChartDataDTO> getMonthlySalesStats();

	BigDecimal getTodayTotalSales();

	@Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM Sale s")
	Double getTotalSalesAmount();


}

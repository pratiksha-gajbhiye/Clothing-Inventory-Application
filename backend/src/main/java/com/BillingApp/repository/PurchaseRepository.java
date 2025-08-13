package com.BillingApp.repository;

import com.BillingApp.DTO.BarChartDTO;
import com.BillingApp.DTO.ChartDataDTO;
import com.BillingApp.DTO.ItemSalesReportDTO;
import com.BillingApp.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("SELECT COALESCE(SUM(p.totalAmount), 0) FROM Purchase p WHERE p.supplier.id = :supplierId")
    BigDecimal sumTotalAmountBySupplierId(@Param("supplierId") Long supplierId);

    @Query("SELECT COALESCE(SUM(p.totalAmount), 0) FROM Purchase p WHERE p.purchaseDate BETWEEN :from AND :to")
    BigDecimal sumTotalAmountBetweenDates(@Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query("SELECT COALESCE(SUM(p.totalAmount), 0) FROM Purchase p")
    BigDecimal getTotalPurchase();

    @Query("SELECT COALESCE(SUM(p.paidAmount), 0) FROM Purchase p")
    BigDecimal getTotalPaidAmount();

    @Query("SELECT COALESCE(SUM(p.dueAmount), 0) FROM Purchase p")
    BigDecimal getTotalDueAmount();

    @Query("""
        SELECT new com.BillingApp.DTO.ItemSalesReportDTO(
            i.name,
            SUM(pi.quantity),
            SUM(pi.totalPrice)
        )
        FROM PurchaseItem pi
        JOIN pi.item i
        JOIN pi.purchase p
        WHERE p.purchaseDate BETWEEN :from AND :to
        GROUP BY i.name
    """)
    List<ItemSalesReportDTO> getItemWisePurchase(@Param("from") LocalDate from, @Param("to") LocalDate to);

    List<Purchase> findByPurchaseDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Purchase> findAllByIsDeletedFalse();

    @Query("""
        SELECT p FROM Purchase p
        WHERE (:supplierName IS NULL OR LOWER(p.supplier.name) LIKE LOWER(CONCAT('%', :supplierName, '%')))
        AND p.purchaseDate BETWEEN :start AND :end
        AND p.isDeleted = false
    """)
    List<Purchase> filterPurchases(@Param("supplierName") String supplierName,
                                   @Param("start") LocalDate start,
                                   @Param("end") LocalDate end);

    @Query("SELECT COALESCE(SUM(p.totalAmount), 0) FROM Purchase p WHERE DATE(p.purchaseDate) = CURRENT_DATE AND p.isDeleted = false")
    BigDecimal getTodayPurchaseAmount();

	List<ChartDataDTO> getMonthlyPurchaseStats();

	List<BarChartDTO> getMonthlyPurchaseBarChart();

	BigDecimal getTodayTotalPurchases();

	@Query("SELECT COALESCE(SUM(p.totalAmount), 0) FROM Purchase p")
	Double getTotalPurchaseAmount();


}

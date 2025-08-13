package com.BillingApp.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BillingApp.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByType(String type);
    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
	List<Payment> findByCustomerIdOrderByPaymentDateAsc(Long customerId);
	List<Payment> findBySupplierIdOrderByPaymentDateAsc(Long supplierId);
	 @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.supplier.id = :supplierId")
	    BigDecimal sumPaymentsBySupplier(@Param("supplierId") Long supplierId);}

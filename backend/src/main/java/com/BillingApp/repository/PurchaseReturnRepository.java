package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.BillingApp.model.PurchaseReturn;

public interface  PurchaseReturnRepository extends JpaRepository<PurchaseReturn, Long> {

	@Query("SELECT SUM(sr.totalAmount) FROM SalesReturn sr")
	Double getTotalReturnAmount();
}



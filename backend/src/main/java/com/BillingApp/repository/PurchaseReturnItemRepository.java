package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.PurchaseReturnItem;

public interface PurchaseReturnItemRepository extends JpaRepository<PurchaseReturnItem, Long> {

}

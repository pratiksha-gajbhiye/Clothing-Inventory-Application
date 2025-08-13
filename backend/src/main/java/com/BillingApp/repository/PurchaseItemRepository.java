package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {}



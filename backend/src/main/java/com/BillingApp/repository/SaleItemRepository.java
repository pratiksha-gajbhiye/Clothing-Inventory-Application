package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {}



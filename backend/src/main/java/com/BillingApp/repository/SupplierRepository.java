package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByMobile(String mobile);
}
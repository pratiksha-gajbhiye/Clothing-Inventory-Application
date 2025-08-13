package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BillingApp.model.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}

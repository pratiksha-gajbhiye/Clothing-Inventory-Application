package com.BillingApp.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByMobile(String mobile);

	long countByCreatedAtAfter(LocalDate minusDays);
}

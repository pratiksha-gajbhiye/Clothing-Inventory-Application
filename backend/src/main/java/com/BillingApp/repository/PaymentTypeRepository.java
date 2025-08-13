package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BillingApp.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}

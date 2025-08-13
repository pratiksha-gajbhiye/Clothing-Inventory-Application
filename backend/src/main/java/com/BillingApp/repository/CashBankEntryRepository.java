package com.BillingApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.CashBankEntry;

public interface CashBankEntryRepository extends JpaRepository<CashBankEntry, Long> {
}

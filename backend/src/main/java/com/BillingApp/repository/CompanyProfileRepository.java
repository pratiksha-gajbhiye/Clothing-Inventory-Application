package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BillingApp.model.CompanyProfile;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long> {
}

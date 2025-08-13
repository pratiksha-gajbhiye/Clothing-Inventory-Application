package com.BillingApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // e.g., Optional<Brand> findByName(String name);
}

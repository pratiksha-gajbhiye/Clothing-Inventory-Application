package com.BillingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.Variant;

public interface VariantRepository extends JpaRepository<Variant, Long> {
    List<Variant> findByQuantityLessThan(int threshold);

}

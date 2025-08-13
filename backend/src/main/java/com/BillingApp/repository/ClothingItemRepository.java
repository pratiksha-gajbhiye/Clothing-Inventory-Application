package com.BillingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.ClothingItem;

public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {

	void deleteById(Long id);

}

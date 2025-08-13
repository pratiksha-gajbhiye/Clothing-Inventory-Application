package com.BillingApp.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.DTO.StockAlertDTO;
import com.BillingApp.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsBySkuCode(String skuCode);
    boolean existsByBarcode(String barcode);
	long countByExpiryDateBefore(LocalDate today);
	long countByStockLessThan(int i);
	List<Item> findByStockLessThan(int i);
	List<StockAlertDTO> countLowStockItems(int i);
	List<StockAlertDTO> getLowStockItems(int i);
}

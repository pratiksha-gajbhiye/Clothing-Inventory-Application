package com.BillingApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BillingApp.model.Item;
import com.BillingApp.model.Warehouse;
import com.BillingApp.model.WarehouseStock;

public interface WarehouseStockRepository extends JpaRepository<WarehouseStock, Long> {
    Optional<WarehouseStock> findByWarehouseAndItem(Warehouse warehouse, Item item);
}
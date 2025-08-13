package com.BillingApp.services;

import java.util.List;

import com.BillingApp.DTO.WarehouseTransferDTO;
import com.BillingApp.model.Warehouse;

public interface WarehouseService {
    List<Warehouse> getAll();
    Warehouse create(Warehouse w);
    void delete(Long id);
    void transferStock(WarehouseTransferDTO dto);
}

package com.BillingApp.services;

import java.util.List;

import com.BillingApp.DTO.SupplierDTO;
import com.BillingApp.model.Supplier;

public interface SupplierService {
    Supplier createSupplier(SupplierDTO dto);
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(Long id);
    void importSuppliers(List<SupplierDTO> dtos);
    List<Supplier> getSuppliersWithDuePayments(); // Placeholder for future billing integration
	Supplier findById(Long supplierId);
	void save(Supplier supplier);
}
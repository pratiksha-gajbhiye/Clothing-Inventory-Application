package com.BillingApp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillingApp.DTO.SupplierDTO;
import com.BillingApp.model.Supplier;
import com.BillingApp.repository.PaymentRepository;
import com.BillingApp.repository.PurchaseRepository;
import com.BillingApp.repository.SupplierRepository;
import com.BillingApp.services.SupplierService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepo;

    @Autowired
    private  PurchaseRepository purchaseRepo;
  
    @Autowired
    private  PaymentRepository paymentRepo;

    @Override
    public Supplier createSupplier(SupplierDTO dto) {
        if (supplierRepo.existsByMobile(dto.getMobile())) {
            throw new RuntimeException("Supplier already exists with mobile: " + dto.getMobile());
        }

        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setMobile(dto.getMobile());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setGstNumber(dto.getGstNumber());
        supplier.setTaxNumber(dto.getTaxNumber());
        supplier.setCountry(dto.getCountry());
        supplier.setState(dto.getState());
        supplier.setCity(dto.getCity());
        supplier.setPostcode(dto.getPostcode());
        supplier.setAddress(dto.getAddress());
        supplier.setOpeningBalance(dto.getOpeningBalance());

        return supplierRepo.save(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    @Override
    public void importSuppliers(List<SupplierDTO> dtos) {
        List<Supplier> suppliers = dtos.stream().map(dto -> {
            Supplier s = new Supplier();
            s.setName(dto.getName());
            s.setMobile(dto.getMobile());
            s.setEmail(dto.getEmail());
            s.setPhone(dto.getPhone());
            s.setGstNumber(dto.getGstNumber());
            s.setTaxNumber(dto.getTaxNumber());
            s.setCountry(dto.getCountry());
            s.setState(dto.getState());
            s.setCity(dto.getCity());
            s.setPostcode(dto.getPostcode());
            s.setAddress(dto.getAddress());
            s.setOpeningBalance(dto.getOpeningBalance());
            return s;
        }).collect(Collectors.toList());

        supplierRepo.saveAll(suppliers);
    }

    @Override
    public List<Supplier> getSuppliersWithDuePayments() {
        // Placeholder for actual logic based on purchase module
        return supplierRepo.findAll();
    }
    
    public BigDecimal getSupplierDueAmount(Long supplierId) {
        BigDecimal totalPurchases = purchaseRepo.sumTotalAmountBySupplierId(supplierId); // custom query
        BigDecimal totalPayments = paymentRepo.sumPaymentsBySupplier(supplierId); // custom query

        if (totalPurchases == null) totalPurchases = BigDecimal.ZERO;
        if (totalPayments == null) totalPayments = BigDecimal.ZERO;

        return totalPurchases.subtract(totalPayments);
    }

    @Override
    public Supplier findById(Long supplierId) {
        return supplierRepo.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + supplierId));
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepo.save(supplier);
    }

}

package com.BillingApp.services;

import java.util.List;

import com.BillingApp.DTO.SaleRequestDTO;
import com.BillingApp.model.Sale;

public interface SaleService {
    Sale createSale(SaleRequestDTO dto);
    List<Sale> getAllSales();
    Sale holdSale(Long saleId);
    Sale resumeSale(Long saleId);
    List<Sale> listSales();

}

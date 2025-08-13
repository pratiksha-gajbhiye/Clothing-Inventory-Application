package com.BillingApp.services;

import com.BillingApp.model.SalesReturnItem;

import java.util.List;

public interface SalesReturnItemService {
    SalesReturnItem save(SalesReturnItem item);
    List<SalesReturnItem> getAll();
    SalesReturnItem getById(Long id);
    void delete(Long id);
    List<SalesReturnItem> findBySalesReturnId(Long salesReturnId);
}

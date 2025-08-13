package com.BillingApp.serviceImpl;


import com.BillingApp.model.SalesReturnItem;
import com.BillingApp.repository.SalesReturnItemRepository;
import com.BillingApp.services.SalesReturnItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesReturnItemServiceImpl implements SalesReturnItemService {

    @Autowired
    private SalesReturnItemRepository repository;

    @Override
    public SalesReturnItem save(SalesReturnItem item) {
        return repository.save(item);
    }

    @Override
    public List<SalesReturnItem> getAll() {
        return repository.findAll();
    }

    @Override
    public SalesReturnItem getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<SalesReturnItem> findBySalesReturnId(Long salesReturnId) {
        return repository.findBySalesReturnId(salesReturnId);
    }
}

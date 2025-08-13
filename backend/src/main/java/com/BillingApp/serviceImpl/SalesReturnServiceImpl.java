package com.BillingApp.serviceImpl;

import com.BillingApp.model.SalesReturn;
import com.BillingApp.repository.SalesReturnRepository;
import com.BillingApp.services.SalesReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesReturnServiceImpl implements SalesReturnService {

    @Autowired
    private SalesReturnRepository salesReturnRepo;

    @Override
    public SalesReturn save(SalesReturn salesReturn) {
        return salesReturnRepo.save(salesReturn);
    }

    @Override
    public List<SalesReturn> getAll() {
        return salesReturnRepo.findAll();
    }

    @Override
    public List<SalesReturn> findByReturnDateBetween(LocalDate from, LocalDate to) {
        return salesReturnRepo.findByReturnDateBetween(from, to);
    }
}

package com.BillingApp.services;

import com.BillingApp.model.SalesReturn;

import java.time.LocalDate;
import java.util.List;

public interface SalesReturnService {

    SalesReturn save(SalesReturn salesReturn);

    List<SalesReturn> getAll();

    List<SalesReturn> findByReturnDateBetween(LocalDate from, LocalDate to);
}

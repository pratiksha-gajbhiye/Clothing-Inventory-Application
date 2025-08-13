package com.BillingApp.services;

import java.time.LocalDate;
import java.util.List;

import com.BillingApp.DTO.LedgerEntryDTO;
import com.BillingApp.DTO.PaymentDTO;
import com.BillingApp.model.Payment;

public interface PaymentService {
    Payment createPayment(PaymentDTO dto);
    List<Payment> filterPayments(String type, LocalDate start, LocalDate end);

    List<LedgerEntryDTO> getLedgerForCustomer(Long customerId);
    List<LedgerEntryDTO> getLedgerForSupplier(Long supplierId);

}
package com.BillingApp.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillingApp.DTO.LedgerEntryDTO;
import com.BillingApp.DTO.PaymentDTO;
import com.BillingApp.model.Customer;
import com.BillingApp.model.Payment;
import com.BillingApp.model.Supplier;
import com.BillingApp.repository.CustomerRepository;
import com.BillingApp.repository.PaymentRepository;
import com.BillingApp.repository.SupplierRepository;
import com.BillingApp.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired private PaymentRepository paymentRepo;
    @Autowired private CustomerRepository customerRepo;
    @Autowired private SupplierRepository supplierRepo;

    @Override
    public Payment createPayment(PaymentDTO dto) {
        Customer customer = dto.getCustomerId() != null ?
            customerRepo.findById(dto.getCustomerId()).orElse(null) : null;

        Supplier supplier = dto.getSupplierId() != null ?
            supplierRepo.findById(dto.getSupplierId()).orElse(null) : null;

        Payment payment = new Payment();
        payment.setType(dto.getType());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMode(dto.getPaymentMode());
        payment.setNote(dto.getNote());
        payment.setCustomer(customer);
        payment.setSupplier(supplier);

        return paymentRepo.save(payment);
    }

    @Override
    public List<Payment> filterPayments(String type, LocalDate start, LocalDate end) {
        if (type != null) {
            return paymentRepo.findByType(type);
        }
        return paymentRepo.findByPaymentDateBetween(start, end);
    }

    @Override
    public List<LedgerEntryDTO> getLedgerForCustomer(Long customerId) {
        List<Payment> payments = paymentRepo.findByCustomerIdOrderByPaymentDateAsc(customerId);
        List<LedgerEntryDTO> ledger = new ArrayList<>();
        BigDecimal balance = BigDecimal.ZERO;

        for (Payment p : payments) {
            BigDecimal credit = BigDecimal.ZERO;
            BigDecimal debit = BigDecimal.ZERO;

            if ("IN".equalsIgnoreCase(p.getType())) {
                credit = p.getAmount();
                balance = balance.add(credit);
            } else {
                debit = p.getAmount();
                balance = balance.subtract(debit);
            }

            LedgerEntryDTO entry = new LedgerEntryDTO();
            entry.setDate(p.getPaymentDate());
            entry.setType(p.getType());
            entry.setPaymentMode(p.getPaymentMode());
            entry.setNote(p.getNote());
            entry.setCredit(credit);
            entry.setDebit(debit);
            entry.setBalance(balance);

            ledger.add(entry);
        }

        return ledger;
    }

    @Override
    public List<LedgerEntryDTO> getLedgerForSupplier(Long supplierId) {
        List<Payment> payments = paymentRepo.findBySupplierIdOrderByPaymentDateAsc(supplierId);
        List<LedgerEntryDTO> ledger = new ArrayList<>();
        BigDecimal balance = BigDecimal.ZERO;

        for (Payment p : payments) {
            BigDecimal credit = BigDecimal.ZERO;
            BigDecimal debit = BigDecimal.ZERO;

            if ("OUT".equalsIgnoreCase(p.getType())) {
                debit = p.getAmount();
                balance = balance.subtract(debit);
            } else {
                credit = p.getAmount();
                balance = balance.add(credit);
            }

            LedgerEntryDTO entry = new LedgerEntryDTO();
            entry.setDate(p.getPaymentDate());
            entry.setType(p.getType());
            entry.setPaymentMode(p.getPaymentMode());
            entry.setNote(p.getNote());
            entry.setCredit(credit);
            entry.setDebit(debit);
            entry.setBalance(balance);

            ledger.add(entry);
        }

        return ledger;
    }
}

package com.BillingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.BillingApp.DTO.LedgerEntryDTO;
import com.BillingApp.DTO.PaymentDTO;
import com.BillingApp.model.Payment;
import com.BillingApp.services.PaymentService;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.BillingApp.util.LedgerExcelExporter;
import com.BillingApp.util.LedgerPdfExporter;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody PaymentDTO dto) {
        return paymentService.createPayment(dto);
    }

    @GetMapping("/filter")
    public List<Payment> filterPayments(
            @RequestParam(required = false) String type,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return paymentService.filterPayments(type, start, end);
    }
    
    @GetMapping("/ledger/customer/{id}")
    public List<LedgerEntryDTO> getCustomerLedger(@PathVariable Long id) {
        return paymentService.getLedgerForCustomer(id);
    }

    @GetMapping("/ledger/supplier/{id}")
    public List<LedgerEntryDTO> getSupplierLedger(@PathVariable Long id) {
        return paymentService.getLedgerForSupplier(id);
    }
    
    @GetMapping("/ledger/export/excel/customer/{id}")
    public ResponseEntity<InputStreamResource> exportCustomerLedgerExcel(@PathVariable Long id) throws IOException {
        List<LedgerEntryDTO> ledger = paymentService.getLedgerForCustomer(id);
        ByteArrayInputStream stream = LedgerExcelExporter.exportToExcel(ledger);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customer_ledger.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(stream));
    }

    @GetMapping("/ledger/export/pdf/customer/{id}")
    public ResponseEntity<InputStreamResource> exportCustomerLedgerPdf(@PathVariable Long id) {
        List<LedgerEntryDTO> ledger = paymentService.getLedgerForCustomer(id);
        ByteArrayInputStream stream = LedgerPdfExporter.exportToPdf(ledger);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customer_ledger.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }


}

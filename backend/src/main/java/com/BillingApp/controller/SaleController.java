package com.BillingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApp.DTO.SaleRequestDTO;
import com.BillingApp.model.Sale;
import com.BillingApp.services.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired private SaleService saleService;

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequestDTO dto) {
        return ResponseEntity.ok(saleService.createSale(dto));
    }

    @PatchMapping("/{id}/hold")
    public ResponseEntity<Sale> holdSale(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.holdSale(id));
    }

    @PatchMapping("/{id}/resume")
    public ResponseEntity<Sale> resumeSale(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.resumeSale(id));
    }

    @GetMapping
    public ResponseEntity<List<Sale>> listSales() {
        return ResponseEntity.ok(saleService.listSales());
    }
}

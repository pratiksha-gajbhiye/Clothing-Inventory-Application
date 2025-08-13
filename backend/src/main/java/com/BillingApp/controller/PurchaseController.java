package com.BillingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.BillingApp.DTO.PurchaseDTO;
import com.BillingApp.services.DashboardService;
import com.BillingApp.services.PurchaseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private DashboardService dashboardService;
    
    // ✅ Create purchase with file upload (multipart)
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> createPurchase(
            @RequestPart("purchase") PurchaseDTO purchaseDTO,
            @RequestPart(value = "invoicePhoto", required = false) MultipartFile invoicePhoto) {

        return ResponseEntity.ok(purchaseService.savePurchase(purchaseDTO, invoicePhoto));
    }

    @GetMapping
    public List<PurchaseDTO> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public PurchaseDTO getPurchaseById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    // ✅ Update purchase with file upload (optional)
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<String> updatePurchase(
            @PathVariable Long id,
            @RequestPart("purchase") PurchaseDTO purchaseDTO,
            @RequestPart(value = "invoicePhoto", required = false) MultipartFile invoicePhoto) {

        return ResponseEntity.ok(purchaseService.updatePurchase(id, purchaseDTO, invoicePhoto));
    }

    @DeleteMapping("/{id}")
    public String deletePurchase(@PathVariable Long id) {
        return purchaseService.deletePurchase(id);
    }

    @GetMapping("/filter")
    public List<PurchaseDTO> filterPurchases(
            @RequestParam(required = false) String supplierName,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return purchaseService.filterPurchases(supplierName, startDate, endDate);
    }

    @GetMapping("/summary")
    public Map<String, Object> getPurchaseSummary() {
        return purchaseService.getPurchaseSummary();
    }

    @PostMapping("/returns")
    public String createPurchaseReturn(@RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.createPurchaseReturn(purchaseDTO);
    }
    
    @GetMapping("/today-purchase")
    public ResponseEntity<BigDecimal> getTodayPurchaseAmount() {
        return ResponseEntity.ok(dashboardService.getTodayPurchaseAmount());
    }

}


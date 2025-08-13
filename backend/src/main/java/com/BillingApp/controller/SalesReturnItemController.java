package com.BillingApp.controller;


import com.BillingApp.model.SalesReturnItem;
import com.BillingApp.services.SalesReturnItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-return-items")
public class SalesReturnItemController {

    @Autowired
    private SalesReturnItemService salesReturnItemService;

    @PostMapping("/save")
    public SalesReturnItem save(@RequestBody SalesReturnItem salesReturnItem) {
        return salesReturnItemService.save(salesReturnItem);
    }

    @GetMapping("/all")
    public List<SalesReturnItem> getAll() {
        return salesReturnItemService.getAll();
    }

    @GetMapping("/{id}")
    public SalesReturnItem getById(@PathVariable Long id) {
        return salesReturnItemService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        salesReturnItemService.delete(id);
    }

    @GetMapping("/by-sales-return/{salesReturnId}")
    public List<SalesReturnItem> getBySalesReturnId(@PathVariable Long salesReturnId) {
        return salesReturnItemService.findBySalesReturnId(salesReturnId);
    }

}

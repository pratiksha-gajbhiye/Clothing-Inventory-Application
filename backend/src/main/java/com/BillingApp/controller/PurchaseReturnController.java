package com.BillingApp.controller;


import com.BillingApp.model.PurchaseReturn;
import com.BillingApp.services.PurchaseReturnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-returns")
@CrossOrigin(origins = "*")
public class PurchaseReturnController {

    private final PurchaseReturnService returnService;

    public PurchaseReturnController(PurchaseReturnService returnService) {
        this.returnService = returnService;
    }

    @GetMapping
    public List<PurchaseReturn> getAllReturns() {
        return returnService.getAllReturns();
    }

    @GetMapping("/{id}")
    public PurchaseReturn getReturnById(@PathVariable Long id) {
        return returnService.getReturnById(id);
    }

    @PostMapping
    public PurchaseReturn createReturn(@RequestBody PurchaseReturn purchaseReturn) {
        return returnService.createReturn(purchaseReturn);
    }

    @DeleteMapping("/{id}")
    public void deleteReturn(@PathVariable Long id) {
        returnService.deleteReturn(id);
    }
}

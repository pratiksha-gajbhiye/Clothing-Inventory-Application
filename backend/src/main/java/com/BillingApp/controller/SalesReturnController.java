package com.BillingApp.controller;

import com.BillingApp.model.SalesReturn;
import com.BillingApp.services.SalesReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales-return")
public class SalesReturnController {

    @Autowired
    private SalesReturnService salesReturnService;

    @PostMapping("/save")
    public SalesReturn save(@RequestBody SalesReturn salesReturn) {
        return salesReturnService.save(salesReturn);
    }

    @GetMapping("/all")
    public List<SalesReturn> getAll() {
        return salesReturnService.getAll();
    }

    @GetMapping("/between")
    public List<SalesReturn> getBetween(
            @RequestParam("from") String from,
            @RequestParam("to") String to
    ) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return salesReturnService.findByReturnDateBetween(fromDate, toDate);
    }
}

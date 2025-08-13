package com.BillingApp.controller;

import org.springframework.web.bind.annotation.*;

import com.BillingApp.DTO.CashBankEntryDTO;
import com.BillingApp.model.CashBankEntry;
import com.BillingApp.services.CashBankEntryService;

import java.util.List;

@RestController
@RequestMapping("/api/cashbank")
@CrossOrigin(origins = "*")
public class CashBankEntryController {

    private final CashBankEntryService service;

    public CashBankEntryController(CashBankEntryService service) {
        this.service = service;
    }

    @PostMapping
    public CashBankEntry create(@RequestBody CashBankEntry entry) {
        return service.createEntry(entry);
    }

    @GetMapping
    public List<CashBankEntry> getAll() {
        return service.getAllEntries();
    }

    @GetMapping("/{id}")
    public CashBankEntry getById(@PathVariable Long id) {
        return service.getEntryById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEntry(id);
    }
    
    @PutMapping("/{id}")
    public CashBankEntryDTO update(@PathVariable Long id, @RequestBody CashBankEntryDTO dto) {
        return service.updateEntry(id, dto);
    }
}

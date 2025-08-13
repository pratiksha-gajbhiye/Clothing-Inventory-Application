package com.BillingApp.serviceImpl;

import org.springframework.stereotype.Service;

import com.BillingApp.DTO.CashBankEntryDTO;
import com.BillingApp.model.CashBankEntry;
import com.BillingApp.repository.CashBankEntryRepository;
import com.BillingApp.services.CashBankEntryService;

import java.util.List;

@Service
public class CashBankEntryServiceImpl implements CashBankEntryService {

    private final CashBankEntryRepository repository;

    public CashBankEntryServiceImpl(CashBankEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CashBankEntry createEntry(CashBankEntry entry) {
        return repository.save(entry);
    }

    @Override
    public List<CashBankEntry> getAllEntries() {
        return repository.findAll();
    }

    @Override
    public CashBankEntry getEntryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteEntry(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CashBankEntryDTO updateEntry(Long id, CashBankEntryDTO dto) {
        CashBankEntry entry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found with ID: " + id));

        entry.setDate(dto.getDate());
        entry.setAccountType(dto.getAccountType());
        entry.setAmount(dto.getAmount());
        entry.setDescription(dto.getDescription());

        CashBankEntry updated = repository.save(entry);
        return mapToDto(updated);
    }

    private CashBankEntryDTO mapToDto(CashBankEntry entry) {
        CashBankEntryDTO dto = new CashBankEntryDTO();
        dto.setId(entry.getId());
        dto.setDate(entry.getDate());
        dto.setAccountType(entry.getAccountType());
        dto.setAmount(entry.getAmount());
        dto.setDescription(entry.getDescription());
        return dto;
    }
}

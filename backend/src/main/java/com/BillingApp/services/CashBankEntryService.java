package com.BillingApp.services;


import java.util.List;

import com.BillingApp.DTO.CashBankEntryDTO;
import com.BillingApp.model.CashBankEntry;

public interface CashBankEntryService {
    CashBankEntry createEntry(CashBankEntry entry);
    List<CashBankEntry> getAllEntries();
    CashBankEntry getEntryById(Long id);
    void deleteEntry(Long id);
    CashBankEntryDTO updateEntry(Long id, CashBankEntryDTO dto);

}

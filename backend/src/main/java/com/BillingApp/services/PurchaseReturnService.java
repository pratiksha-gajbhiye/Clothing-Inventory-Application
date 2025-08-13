package com.BillingApp.services;

import com.BillingApp.DTO.PurchaseReturnDTO;
import com.BillingApp.model.PurchaseReturn;

import java.util.List;

public interface PurchaseReturnService {

    // Add this missing method 👇
    PurchaseReturn createReturn(PurchaseReturnDTO dto);

//    PurchaseReturn createReturn(PurchaseReturn purchaseReturn);

    List<PurchaseReturn> getAllReturns();

    PurchaseReturn getReturnById(Long id);

    PurchaseReturn updateReturn(Long id, PurchaseReturnDTO dto);

    void deleteReturn(Long id);

	PurchaseReturn createReturn(PurchaseReturn purchaseReturn);
}

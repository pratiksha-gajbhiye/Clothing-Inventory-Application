package com.BillingApp.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.BillingApp.DTO.PurchaseDTO;

public interface PurchaseService {
    String savePurchase(PurchaseDTO purchaseDTO);

    List<PurchaseDTO> filterPurchases(String supplierName, String startDate, String endDate);

	Map<String, Object> getPurchaseSummary();

	String createPurchaseReturn(PurchaseDTO purchaseDTO);

	List<PurchaseDTO> getAllPurchases();

	PurchaseDTO getPurchaseById(Long id);

	String updatePurchase(Long id, PurchaseDTO purchaseDTO);

	String deletePurchase(Long id);

    String savePurchase(PurchaseDTO purchaseDTO, MultipartFile invoicePhoto);

	String updatePurchase(Long id, PurchaseDTO purchaseDTO, MultipartFile invoicePhoto);

	
}
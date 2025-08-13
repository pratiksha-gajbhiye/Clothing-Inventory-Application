package com.BillingApp.serviceImpl;

import com.BillingApp.DTO.PurchaseDTO;
import com.BillingApp.DTO.PurchaseItemDTO;
import com.BillingApp.enum1.PurchaseStatus;
import com.BillingApp.model.Item;
import com.BillingApp.model.Purchase;
import com.BillingApp.model.PurchaseItem;
import com.BillingApp.model.Supplier;
import com.BillingApp.repository.ItemRepository;
import com.BillingApp.repository.PurchaseRepository;
import com.BillingApp.repository.SupplierRepository;
import com.BillingApp.services.PurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private  PurchaseRepository purchaseRepository;
    private SupplierRepository supplierRepository;
    private  ItemRepository itemRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    @Transactional
    public String savePurchase(PurchaseDTO purchaseDTO) {
        return savePurchase(purchaseDTO, purchaseDTO.getInvoiceFile());
    }

    @Override
    @Transactional
    public String savePurchase(PurchaseDTO purchaseDTO, MultipartFile invoicePhoto) {
        Purchase purchase = new Purchase();

        Supplier supplier = supplierRepository.findById(purchaseDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        populatePurchaseFromDTO(purchase, purchaseDTO, supplier);

        if (invoicePhoto != null && !invoicePhoto.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + invoicePhoto.getOriginalFilename();
            try {
                invoicePhoto.transferTo(new File(uploadPath + fileName));
                purchase.setInvoicePhoto(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Error saving invoice photo", e);
            }
        }

        purchaseRepository.save(purchase);
        return "Purchase saved successfully";
    }

    @Override
    @Transactional
    public String updatePurchase(Long id, PurchaseDTO purchaseDTO) {
        return updatePurchase(id, purchaseDTO, purchaseDTO.getInvoiceFile());
    }

    @Override
    @Transactional
    public String updatePurchase(Long id, PurchaseDTO purchaseDTO, MultipartFile invoicePhoto) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));

        Supplier supplier = supplierRepository.findById(purchaseDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        populatePurchaseFromDTO(purchase, purchaseDTO, supplier);

        if (invoicePhoto != null && !invoicePhoto.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + invoicePhoto.getOriginalFilename();
            try {
                invoicePhoto.transferTo(new File(uploadPath + fileName));
                purchase.setInvoicePhoto(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Error saving invoice photo", e);
            }
        }

        purchaseRepository.save(purchase);
        return "Purchase updated successfully";
    }

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        return purchaseRepository.findAllByIsDeletedFalse().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO getPurchaseById(Long id) {
        return purchaseRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
    }

    @Override
    @Transactional
    public String deletePurchase(Long id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
        purchase.setDeleted(true);
        purchaseRepository.save(purchase);
        return "Purchase deleted successfully";
    }

//    @Override
//    public List<PurchaseDTO> filterPurchases(String supplierName, String startDate, String endDate) {
//        return purchaseRepository.filterPurchases(supplierName, startDate, endDate).stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public Map<String, Object> getPurchaseSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalPurchase", purchaseRepository.getTotalPurchase());
        summary.put("totalPaid", purchaseRepository.getTotalPaidAmount());
        summary.put("totalDue", purchaseRepository.getTotalDueAmount());
        return summary;
    }

    @Override
    @Transactional
    public String createPurchaseReturn(PurchaseDTO purchaseDTO) {
        purchaseDTO.setStatus("RETURNED");
        return savePurchase(purchaseDTO);
    }

    private void populatePurchaseFromDTO(Purchase purchase, PurchaseDTO dto, Supplier supplier) {
        purchase.setInvoiceNumber(dto.getInvoiceNumber());
        purchase.setVoucherNumber(dto.getVoucherNumber());
        purchase.setPurchaseDate(dto.getPurchaseDate());
        purchase.setSupplier(supplier);
        purchase.setPaymentType(dto.getPaymentType());
        purchase.setRemarks(dto.getRemarks());

        purchase.setDiscountAmount(Optional.ofNullable(dto.getDiscountAmount()).orElse(BigDecimal.ZERO));
        purchase.setGstAmount(Optional.ofNullable(dto.getGstAmount()).orElse(BigDecimal.ZERO));
        purchase.setRoundOffAmount(Optional.ofNullable(dto.getRoundOffAmount()).orElse(BigDecimal.ZERO));

        purchase.setStatus(Optional.ofNullable(dto.getStatus())
                .map(s -> PurchaseStatus.valueOf(s.toUpperCase()))
                .orElse(PurchaseStatus.PENDING));

        BigDecimal total = Optional.ofNullable(dto.getTotalAmount()).orElse(BigDecimal.ZERO);
        BigDecimal paid = Optional.ofNullable(dto.getPaidAmount()).orElse(BigDecimal.ZERO);
        BigDecimal due = total.subtract(paid);

        purchase.setTotalAmount(total);
        purchase.setPaidAmount(paid);
        purchase.setDueAmount(due);

        purchase.setCreatedBy("SYSTEM");
        purchase.setUpdatedBy("SYSTEM");

        if (purchase.getCreatedAt() == null)
            purchase.setCreatedAt(LocalDateTime.now());

        purchase.setUpdatedAt(LocalDateTime.now());

        List<PurchaseItem> items = dto.getItems().stream().map(itemDTO -> {
            Item item = itemRepository.findById(itemDTO.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            // Update stock
            item.setStock(item.getStock() + itemDTO.getQuantity());
            itemRepository.save(item);

            PurchaseItem pi = new PurchaseItem();
            pi.setItem(item);
            pi.setPurchase(purchase);
            pi.setItemName(item.getName());
            pi.setQuantity(itemDTO.getQuantity());
            pi.setUnitPrice(itemDTO.getUnitPrice());
            pi.setTotalPrice(itemDTO.getTotalPrice());
            return pi;
        }).collect(Collectors.toList());

        purchase.setItems(items);
    }

    private PurchaseDTO convertToDTO(Purchase purchase) {
        PurchaseDTO dto = new PurchaseDTO();
        dto.setInvoiceNumber(purchase.getInvoiceNumber());
        dto.setVoucherNumber(purchase.getVoucherNumber());
        dto.setPurchaseDate(purchase.getPurchaseDate());
        dto.setPaymentType(purchase.getPaymentType());
        dto.setRemarks(purchase.getRemarks());
        dto.setTotalAmount(purchase.getTotalAmount());
        dto.setPaidAmount(purchase.getPaidAmount());
        dto.setDueAmount(purchase.getDueAmount());
        dto.setDiscountAmount(purchase.getDiscountAmount());
        dto.setGstAmount(purchase.getGstAmount());
        dto.setRoundOffAmount(purchase.getRoundOffAmount());
        dto.setStatus(purchase.getStatus().name());
        dto.setSupplierId(purchase.getSupplier().getId());

        List<PurchaseItemDTO> itemDTOs = purchase.getItems().stream().map(item -> {
            PurchaseItemDTO dtoItem = new PurchaseItemDTO();
            dtoItem.setItemId(item.getItem().getId());
            dtoItem.setItemName(item.getItemName());
            dtoItem.setQuantity(item.getQuantity());
            dtoItem.setUnitPrice(item.getUnitPrice());
            dtoItem.setTotalPrice(item.getTotalPrice());
            return dtoItem;
        }).collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }

    @Override
    public List<PurchaseDTO> filterPurchases(String supplierName, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate, formatter) : null;
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate, formatter) : null;

        List<Purchase> filteredPurchases = purchaseRepository.filterPurchases(supplierName, start, end);
        return filteredPurchases.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}

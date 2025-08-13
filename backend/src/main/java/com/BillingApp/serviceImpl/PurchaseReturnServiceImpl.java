package com.BillingApp.serviceImpl;

import com.BillingApp.DTO.PurchaseReturnDTO;
import com.BillingApp.DTO.PurchaseReturnItemDTO;
import com.BillingApp.enum1.PaymentStatus;
import com.BillingApp.model.Item;
import com.BillingApp.model.PurchaseReturn;
import com.BillingApp.model.PurchaseReturnItem;
import com.BillingApp.model.Supplier;
import com.BillingApp.repository.ItemRepository;
import com.BillingApp.repository.PurchaseReturnItemRepository;
import com.BillingApp.repository.PurchaseReturnRepository;
import com.BillingApp.repository.SupplierRepository;
import com.BillingApp.services.PurchaseReturnService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseReturnServiceImpl implements PurchaseReturnService {

    private final PurchaseReturnRepository returnRepo;
    private final PurchaseReturnItemRepository itemRepo;
    private final SupplierRepository supplierRepo;
    private final ItemRepository itemRepository;

    
    
    public PurchaseReturnServiceImpl(PurchaseReturnRepository returnRepo, PurchaseReturnItemRepository itemRepo,
			SupplierRepository supplierRepo, ItemRepository itemRepository) {
		super();
		this.returnRepo = returnRepo;
		this.itemRepo = itemRepo;
		this.supplierRepo = supplierRepo;
		this.itemRepository = itemRepository;
	}

	@Override
    @Transactional
    public PurchaseReturn createReturn(PurchaseReturnDTO dto) {
        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        PurchaseReturn ret = new PurchaseReturn();
        ret.setReferenceNumber(dto.getReferenceNumber());
        ret.setReturnDate(dto.getReturnDate());
        ret.setReturnReason(dto.getReturnReason());
        ret.setPaymentStatus(PaymentStatus.valueOf(dto.getPaymentStatus()));
        ret.setTax(dto.getTax());
        ret.setSupplier(supplier);
        ret.setAttachmentPath(dto.getAttachmentPath());
        ret.setCreatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        List<PurchaseReturnItem> returnItems = new ArrayList<>();

        for (PurchaseReturnItemDTO itemDTO : dto.getItems()) {
            Item item = itemRepository.findById(itemDTO.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            BigDecimal subtotal = itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));

            PurchaseReturnItem returnItem = new PurchaseReturnItem(null, ret, item,
                    itemDTO.getQuantity(), itemDTO.getUnitPrice(), subtotal);

            returnItems.add(returnItem);
            total = total.add(subtotal);

            item.setStock(item.getStock() + itemDTO.getQuantity()); // Return increases stock
            itemRepository.save(item);
        }

        ret.setTotalAmount(total);
        ret.setNetAmount(total.add(dto.getTax()));
        ret.setItems(returnItems);

        return returnRepo.save(ret);
    }

    @Override
    public PurchaseReturn createReturn(PurchaseReturn purchaseReturn) {
        return returnRepo.save(purchaseReturn);
    }

    @Override
    public List<PurchaseReturn> getAllReturns() {
        return returnRepo.findAll();
    }

    @Override
    public PurchaseReturn getReturnById(Long id) {
        return returnRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Return not found"));
    }

    @Override
    @Transactional
    public PurchaseReturn updateReturn(Long id, PurchaseReturnDTO dto) {
        PurchaseReturn existing = returnRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Return not found"));

        // Revert previous stock changes
        for (PurchaseReturnItem oldItem : existing.getItems()) {
            Item item = oldItem.getItem();
            item.setStock(item.getStock() - oldItem.getQuantity());
            itemRepository.save(item);
        }

        itemRepo.deleteAll(existing.getItems());

        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        existing.setReferenceNumber(dto.getReferenceNumber());
        existing.setReturnDate(dto.getReturnDate());
        existing.setReturnReason(dto.getReturnReason());
        existing.setPaymentStatus(PaymentStatus.valueOf(dto.getPaymentStatus()));
        existing.setTax(dto.getTax());
        existing.setAttachmentPath(dto.getAttachmentPath());
        existing.setSupplier(supplier);
        existing.setUpdatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        List<PurchaseReturnItem> updatedItems = new ArrayList<>();

        for (PurchaseReturnItemDTO itemDTO : dto.getItems()) {
            Item item = itemRepository.findById(itemDTO.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            BigDecimal subtotal = itemDTO.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));

            PurchaseReturnItem returnItem = new PurchaseReturnItem(null, existing, item,
                    itemDTO.getQuantity(), itemDTO.getUnitPrice(), subtotal);

            updatedItems.add(returnItem);
            total = total.add(subtotal);

            item.setStock(item.getStock() + itemDTO.getQuantity());
            itemRepository.save(item);
        }

        existing.setItems(updatedItems);
        existing.setTotalAmount(total);
        existing.setNetAmount(total.add(dto.getTax()));

        return returnRepo.save(existing);
    }

    @Override
    @Transactional
    public void deleteReturn(Long id) {
        PurchaseReturn ret = returnRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Return not found"));

        for (PurchaseReturnItem item : ret.getItems()) {
            Item dbItem = item.getItem();
            dbItem.setStock(dbItem.getStock() - item.getQuantity()); // Revert stock
            itemRepository.save(dbItem);
        }

        itemRepo.deleteAll(ret.getItems());
        returnRepo.delete(ret);
    }
}

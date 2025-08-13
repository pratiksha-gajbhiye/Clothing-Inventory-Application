package com.BillingApp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BillingApp.DTO.SaleRequestDTO;
import com.BillingApp.enum1.SaleStatus;
import com.BillingApp.model.Customer;
import com.BillingApp.model.Item;
import com.BillingApp.model.Sale;
import com.BillingApp.model.SaleItem;
import com.BillingApp.repository.CustomerRepository;
import com.BillingApp.repository.ItemRepository;
import com.BillingApp.repository.SaleRepository;
import com.BillingApp.services.SaleService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired private SaleRepository saleRepo;
    @Autowired private ItemRepository itemRepo;
    @Autowired private CustomerRepository customerRepo;

    @Override
    @Transactional
    public Sale createSale(SaleRequestDTO dto) {
        Customer cust = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Sale sale = new Sale();
        sale.setInvoiceNumber(dto.getInvoiceNumber());
        sale.setSaleDate(dto.getSaleDate());
        sale.setCustomer(cust);
        sale.setStatus(SaleStatus.COMPLETED);

        List<SaleItem> items = dto.getItems().stream().map(i -> {
            Item item = itemRepo.findById(i.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            // Decrease item stock
            if (item.getStock() < i.getQuantity()) {
                throw new RuntimeException("Insufficient stock for item: " + item.getName());
            }
            item.setStock(item.getStock() - i.getQuantity());
            itemRepo.save(item);

            // Create SaleItem
            SaleItem si = new SaleItem();
            si.setSale(sale);
            si.setItem(item);
            si.setQuantity(i.getQuantity());

            BigDecimal unitPrice = BigDecimal.valueOf(i.getPrice());
            BigDecimal discount = BigDecimal.valueOf(i.getDiscount());
            BigDecimal tax = BigDecimal.valueOf(i.getTax());
            BigDecimal quantity = BigDecimal.valueOf(i.getQuantity());

            si.setUnitPrice(unitPrice);
            si.setDiscount(discount);
            si.setTax(tax);

            BigDecimal subtotal = unitPrice.multiply(quantity).subtract(discount).add(tax);
            si.setSubtotal(subtotal);

            return si;
        }).collect(Collectors.toList());

        // Set items to sale
        sale.setItems(items);

        // Totals using BigDecimal
        BigDecimal totalAmount = items.stream()
                .map(si -> si.getUnitPrice().multiply(BigDecimal.valueOf(si.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDiscount = items.stream()
                .map(SaleItem::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalTax = items.stream()
                .map(SaleItem::getTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal grandTotal = totalAmount.subtract(totalDiscount).add(totalTax);

        sale.setTotalAmount(totalAmount);
        sale.setTotalDiscount(totalDiscount);
        sale.setTotalTax(totalTax);
        sale.setGrandTotal(grandTotal);

        return saleRepo.save(sale);
    }

    @Override
    public Sale holdSale(Long saleId) {
        Sale sale = saleRepo.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        sale.setStatus(SaleStatus.PENDING);
        return saleRepo.save(sale);
    }

    @Override
    public Sale resumeSale(Long saleId) {
        Sale sale = saleRepo.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        sale.setStatus(SaleStatus.COMPLETED);
        return saleRepo.save(sale);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepo.findAll();
    }

    @Override
    public List<Sale> listSales() {
        return saleRepo.findAll();
    }
}

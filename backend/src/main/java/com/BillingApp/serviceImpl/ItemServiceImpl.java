package com.BillingApp.serviceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillingApp.DTO.ItemDTO;
import com.BillingApp.model.Item;
import com.BillingApp.repository.BrandRepository;
import com.BillingApp.repository.CategoryRepository;
import com.BillingApp.repository.ItemRepository;
import com.BillingApp.services.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private BrandRepository brandRepo;

    @Autowired
    private CategoryRepository categoryRepo;


    // Save simple item directly
    @Override
    public Item createItem(Item item) {
        return itemRepo.save(item);
    }

    // Add Item via DTO
    @Override
    public ItemDTO addItem(ItemDTO dto) {
        Item item = toEntity(dto);
        itemRepo.save(item);
        return toDTO(item);
    }

    // Update existing item
    @Override
    public ItemDTO updateItem(Long id, ItemDTO dto) {
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setName(dto.getName());
        item.setSize(dto.getSize());
        item.setColor(dto.getColor());
        item.setSellingPrice(dto.getSellingPrice());
        item.setCostPrice(dto.getCostPrice());
        item.setGstPercentage(dto.getGstPercentage());
        item.setStock(dto.getStock());
        item.setBarcode(dto.getBarcode());
        item.setSkuCode(dto.getSkuCode());
        item.setBrand(brandRepo.findById(dto.getBrandId()).orElse(null));
        item.setCategory(categoryRepo.findById(dto.getCategoryId()).orElse(null));

        itemRepo.save(item);
        return toDTO(item);
    }

    // Get all items as DTO
    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Delete item
    @Override
    public void deleteItem(Long id) {
        itemRepo.deleteById(id);
    }

    // Save all items
    @Override
    public void saveAll(List<Item> items) {
        itemRepo.saveAll(items);
    }

    // Convert DTO to Entity
    private Item toEntity(ItemDTO dto) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setSize(dto.getSize());
        item.setColor(dto.getColor());
        item.setStock(dto.getStock());
        item.setCostPrice(dto.getCostPrice());
        item.setSellingPrice(dto.getSellingPrice());
        item.setGstPercentage(dto.getGstPercentage());
        item.setSkuCode(dto.getSkuCode());
        item.setBarcode(dto.getBarcode());
        item.setBrand(brandRepo.findById(dto.getBrandId()).orElse(null));
        item.setCategory(categoryRepo.findById(dto.getCategoryId()).orElse(null));
        return item;
    }

    // Convert Entity to DTO
    private ItemDTO toDTO(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setSize(item.getSize());
        dto.setColor(item.getColor());
        dto.setCostPrice(item.getCostPrice());
        dto.setSellingPrice(item.getSellingPrice());
        dto.setGstPercentage(item.getGstPercentage());
        dto.setStock(item.getStock());
        dto.setSkuCode(item.getSkuCode());
        dto.setBarcode(item.getBarcode());
        dto.setBrandId(item.getBrand() != null ? item.getBrand().getId() : null);
        dto.setCategoryId(item.getCategory() != null ? item.getCategory().getId() : null);
        return dto;
    }
}

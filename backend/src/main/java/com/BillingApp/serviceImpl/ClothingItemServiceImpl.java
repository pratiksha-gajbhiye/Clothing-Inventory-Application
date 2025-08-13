package com.BillingApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillingApp.model.ClothingItem;
import com.BillingApp.model.Variant;
import com.BillingApp.repository.BrandRepository;
import com.BillingApp.repository.CategoryRepository;
import com.BillingApp.repository.ClothingItemRepository;
import com.BillingApp.services.ClothingItemService;

@Service
public class ClothingItemServiceImpl implements ClothingItemService {

    @Autowired private ClothingItemRepository itemRepo;
    @Autowired private CategoryRepository categoryRepo;
    @Autowired private BrandRepository brandRepo;

    @Override
    public ClothingItem createClothingItem(ClothingItem dto) {
        ClothingItem item = new ClothingItem();
        item.setName(dto.getName());
        item.setGender(dto.getGender());
        item.setImageUrl(dto.getImageUrl());
        item.setCategory(categoryRepo.findById(dto.getCategoryId()).orElseThrow());
        item.setBrand(brandRepo.findById(dto.getBrandId()).orElseThrow());

        List<Variant> variants = dto.getVariants().stream().map(v -> {
            Variant variant = new Variant();
            variant.setSize(v.getSize());
            variant.setColor(v.getColor());
            variant.setQuantity(v.getQuantity());
            variant.setPurchasePrice(v.getPurchasePrice());
            variant.setSellingPrice(v.getSellingPrice());
            variant.setClothingItem(item);
            return variant;
        }).collect(Collectors.toList());

        item.setVariants(variants);
        return itemRepo.save(item);
    }

    @Override
    public List<ClothingItem> getAllClothingItems() {
        return itemRepo.findAll();
    }

    @Override
    public ClothingItem getClothingItem(Long id) {
        return itemRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteClothingItem(Long id) {
        itemRepo.deleteById(id);
    }


}

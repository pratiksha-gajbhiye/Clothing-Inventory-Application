package com.BillingApp.services;

import java.util.List;

import com.BillingApp.model.ClothingItem;

public interface ClothingItemService {
    ClothingItem createClothingItem(ClothingItem dto);
    List<ClothingItem> getAllClothingItems();
    ClothingItem getClothingItem(Long id);
    void deleteClothingItem(Long id);
}

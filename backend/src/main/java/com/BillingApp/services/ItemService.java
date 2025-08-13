package com.BillingApp.services;


import java.util.List;

import com.BillingApp.DTO.ItemDTO;
import com.BillingApp.model.Item;

public interface ItemService {
    Item createItem(Item item);

    ItemDTO addItem(ItemDTO dto);
    ItemDTO updateItem(Long id, ItemDTO dto);
    List<ItemDTO> getAllItems();
    void deleteItem(Long id);
    void saveAll(List<Item> items);

}

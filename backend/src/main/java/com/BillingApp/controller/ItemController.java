package com.BillingApp.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.BillingApp.DTO.ItemDTO;
import com.BillingApp.model.Brand;
import com.BillingApp.model.Category;
import com.BillingApp.model.Item;
import com.BillingApp.repository.BrandRepository;
import com.BillingApp.repository.CategoryRepository;
import com.BillingApp.services.ItemService;
import com.BillingApp.util.ExcelImportUtil;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService ;

    private final BrandRepository brandRepository;
    
    private final CategoryRepository categoryRepository;
   
    
    public ItemController(ItemService itemService, BrandRepository brandRepository,
			CategoryRepository categoryRepository) {
		super();
		this.itemService = itemService;
		this.brandRepository = brandRepository;
		this.categoryRepository = categoryRepository;
	}

	@PostMapping
    public ItemDTO create(@RequestBody ItemDTO dto) {
        return itemService.addItem(dto);
    }

    @PutMapping("/{id}")
    public ItemDTO update(@PathVariable Long id, @RequestBody ItemDTO dto) {
        return itemService.updateItem(id, dto);
    }

    @GetMapping
    public List<ItemDTO> getAll() {
        return itemService.getAllItems();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
    
    @PostMapping("/items/import")
    public ResponseEntity<String> importItems(@RequestParam("file") MultipartFile file,
                                              @RequestParam Long brandId,
                                              @RequestParam Long categoryId) {
        try {
            Brand brand = brandRepository.findById(brandId)
                    .orElseThrow(() -> new RuntimeException("Brand not found"));
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            List<Item> items = ExcelImportUtil.parseExcelFile(file.getInputStream(), brand, category);
            itemService.saveAll(items);

            return ResponseEntity.ok("Import successful! Total items: " + items.size());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import: " + e.getMessage());
        }
    }
    
    

}

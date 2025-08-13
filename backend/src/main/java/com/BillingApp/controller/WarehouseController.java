package com.BillingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BillingApp.DTO.WarehouseTransferDTO;
import com.BillingApp.model.Warehouse;
import com.BillingApp.services.WarehouseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

	@Autowired
    private  WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAll() {
        return ResponseEntity.ok(warehouseService.getAll());
    }

    @PostMapping
    public ResponseEntity<Warehouse> create(@RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseService.create(warehouse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferStock(@RequestBody WarehouseTransferDTO dto) {
        warehouseService.transferStock(dto);
        return ResponseEntity.ok("Stock transferred successfully.");
    }
}

package com.BillingApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BillingApp.DTO.WarehouseTransferDTO;
import com.BillingApp.model.Item;
import com.BillingApp.model.Warehouse;
import com.BillingApp.model.WarehouseStock;
import com.BillingApp.repository.ItemRepository;
import com.BillingApp.repository.WarehouseRepository;
import com.BillingApp.repository.WarehouseStockRepository;
import com.BillingApp.services.WarehouseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

	@Autowired
    private WarehouseRepository warehouseRepo;
   
	@Autowired
	private WarehouseStockRepository stockRepo;
   
	@Autowired
	private ItemRepository itemRepo;

    @Override
    public List<Warehouse> getAll() {
        return warehouseRepo.findAll();
    }

    @Override
    public Warehouse create(Warehouse w) {
        return warehouseRepo.save(w);
    }

    @Override
    public void delete(Long id) {
        warehouseRepo.deleteById(id);
    }

    @Override
    public void transferStock(WarehouseTransferDTO dto) {
        Item item = itemRepo.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Warehouse from = warehouseRepo.findById(dto.getFromWarehouseId())
                .orElseThrow(() -> new RuntimeException("From warehouse not found"));

        Warehouse to = warehouseRepo.findById(dto.getToWarehouseId())
                .orElseThrow(() -> new RuntimeException("To warehouse not found"));

        WarehouseStock fromStock = stockRepo.findByWarehouseAndItem(from, item)
                .orElseThrow(() -> new RuntimeException("No stock in source warehouse"));

        if (fromStock.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Insufficient stock in source warehouse");
        }

        fromStock.setQuantity(fromStock.getQuantity() - dto.getQuantity());
        stockRepo.save(fromStock);

        WarehouseStock toStock = stockRepo.findByWarehouseAndItem(to, item)
                .orElse(new WarehouseStock(null, to, item, 0));
        toStock.setQuantity(toStock.getQuantity() + dto.getQuantity());
        stockRepo.save(toStock);
    }

	
}

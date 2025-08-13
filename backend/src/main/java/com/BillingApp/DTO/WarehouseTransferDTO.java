package com.BillingApp.DTO;

import lombok.Data;

@Data
public class WarehouseTransferDTO {
    private Long itemId;
    private Long fromWarehouseId;
    private Long toWarehouseId;
    private Integer quantity;
	
    public WarehouseTransferDTO() {
		super();
	}

	public WarehouseTransferDTO(Long itemId, Long fromWarehouseId, Long toWarehouseId, Integer quantity) {
		super();
		this.itemId = itemId;
		this.fromWarehouseId = fromWarehouseId;
		this.toWarehouseId = toWarehouseId;
		this.quantity = quantity;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getFromWarehouseId() {
		return fromWarehouseId;
	}

	public void setFromWarehouseId(Long fromWarehouseId) {
		this.fromWarehouseId = fromWarehouseId;
	}

	public Long getToWarehouseId() {
		return toWarehouseId;
	}

	public void setToWarehouseId(Long toWarehouseId) {
		this.toWarehouseId = toWarehouseId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



}

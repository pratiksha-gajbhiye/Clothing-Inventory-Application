package com.BillingApp.DTO;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class SalesReturnItemDTO {
    private Long itemId;
    private Integer quantity;
    private Double refundAmount;
	
    public SalesReturnItemDTO() {
		super();
	}

	public SalesReturnItemDTO(Long itemId, Integer quantity, Double refundAmount) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.refundAmount = refundAmount;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}


    


}

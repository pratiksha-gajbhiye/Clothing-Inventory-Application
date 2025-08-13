package com.BillingApp.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockAlertDTO {
    private String itemName;
    private int quantity;
    private LocalDate expiryDate;  // ✅ Newly added field

   

	public StockAlertDTO() {
		super();
	}

	
	public StockAlertDTO(String itemName, int quantity, LocalDate expiryDate) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.expiryDate = expiryDate;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public LocalDate getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}


}
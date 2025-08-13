package com.BillingApp.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Variant {
    @Id @GeneratedValue
    private Long id;

    private String size; // S, M, L, XL
    private String color;
    private int quantity;
    private int minStock = 5; // for low stock alert
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;

    @ManyToOne
    private ClothingItem clothingItem;

	public Variant() {
	}

	public Variant(Long id, String size, String color, int quantity, int minStock, BigDecimal purchasePrice,
			BigDecimal sellingPrice, ClothingItem clothingItem) {
		super();
		this.id = id;
		this.size = size;
		this.color = color;
		this.quantity = quantity;
		this.minStock = minStock;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.clothingItem = clothingItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public ClothingItem getClothingItem() {
		return clothingItem;
	}

	public void setClothingItem(ClothingItem clothingItem) {
		this.clothingItem = clothingItem;
	}


    
}

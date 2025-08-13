package com.BillingApp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
public class PurchaseItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String itemName;
  private Integer quantity;
  private BigDecimal unitPrice;
  private BigDecimal totalPrice;

  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;
 
  @ManyToOne
  @JoinColumn(name = "purchase_id")
  private Purchase purchase;

  public PurchaseItem() {
}

public PurchaseItem(Long id, String itemName, Integer quantity, BigDecimal unitPrice, BigDecimal totalPrice,
		Purchase purchase) {
	super();
	this.id = id;
	this.itemName = itemName;
	this.quantity = quantity;
	this.unitPrice = unitPrice;
	this.totalPrice = totalPrice;
	this.purchase = purchase;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public Integer getQuantity() {
	return quantity;
}

public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}

public BigDecimal getUnitPrice() {
	return unitPrice;
}

public void setUnitPrice(BigDecimal unitPrice) {
	this.unitPrice = unitPrice;
}

public BigDecimal getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(BigDecimal totalPrice) {
	this.totalPrice = totalPrice;
}

public Purchase getPurchase() {
	return purchase;
}

public void setPurchase(Purchase purchase) {
	this.purchase = purchase;
}

public Item getItem() {
	return item;
}

public void setItem(Item item) {
	this.item = item;
}

  
  
}

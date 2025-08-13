package com.BillingApp.model;

import java.math.BigDecimal;

//model/PurchaseReturnItem.java

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReturnItem {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private int quantity;
 private double rate;
 private double gst;
 private double discount;
 private double total;
 private BigDecimal unitPrice;

 @ManyToOne
 @JoinColumn(name = "item_id")
 private Item item;

 @ManyToOne
 @JoinColumn(name = "purchase_return_id")
 private PurchaseReturn purchaseReturn;

public PurchaseReturnItem(Object object, PurchaseReturn ret, Item item2, int i, BigDecimal bigDecimal, BigDecimal subtotal) {
	super();
}


public PurchaseReturnItem(Long id, int quantity, double rate, double gst, double discount, double total,
		BigDecimal unitPrice, Item item, PurchaseReturn purchaseReturn) {
	super();
	this.id = id;
	this.quantity = quantity;
	this.rate = rate;
	this.gst = gst;
	this.discount = discount;
	this.total = total;
	this.unitPrice = unitPrice;
	this.item = item;
	this.purchaseReturn = purchaseReturn;
}







public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public double getRate() {
	return rate;
}

public void setRate(double rate) {
	this.rate = rate;
}

public double getGst() {
	return gst;
}

public void setGst(double gst) {
	this.gst = gst;
}

public double getDiscount() {
	return discount;
}

public void setDiscount(double discount) {
	this.discount = discount;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

public Item getItem() {
	return item;
}

public void setItem(Item item) {
	this.item = item;
}

public PurchaseReturn getPurchaseReturn() {
	return purchaseReturn;
}

public void setPurchaseReturn(PurchaseReturn purchaseReturn) {
	this.purchaseReturn = purchaseReturn;
}

public BigDecimal getUnitPrice() {
	return unitPrice;
}

public void setUnitPrice(BigDecimal unitPrice) {
	this.unitPrice = unitPrice;
}



}

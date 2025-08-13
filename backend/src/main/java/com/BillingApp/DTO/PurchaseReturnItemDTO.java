package com.BillingApp.DTO;

import java.math.BigDecimal;

//dto/PurchaseReturnItemDTO.java

import lombok.Data;

@Data
public class PurchaseReturnItemDTO {
	  private Long itemId;
	  private int quantity;
	  private double rate;
	  private double gst;
	  private double discount;
	  private BigDecimal unitPrice;


public PurchaseReturnItemDTO() {
	}


public PurchaseReturnItemDTO(Long itemId, int quantity, double rate, double gst, double discount,
		BigDecimal unitPrice) {
	this.itemId = itemId;
	this.quantity = quantity;
	this.rate = rate;
	this.gst = gst;
	this.discount = discount;
	this.unitPrice = unitPrice;
}


public Long getItemId() {
	return itemId;
}
public void setItemId(Long itemId) {
	this.itemId = itemId;
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
public BigDecimal getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(BigDecimal unitPrice) {
	this.unitPrice = unitPrice;
}



 
}

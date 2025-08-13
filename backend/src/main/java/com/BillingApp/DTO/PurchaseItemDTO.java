package com.BillingApp.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PurchaseItemDTO {
    private Long itemId;
    private String itemName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal gst;
    private BigDecimal discount;
    private BigDecimal totalPrice;

    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getGst() {
        return gst;
    }
    public void setGst(BigDecimal gst) {
        this.gst = gst;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal bigDecimal) {
        this.totalPrice = bigDecimal;
    }
}

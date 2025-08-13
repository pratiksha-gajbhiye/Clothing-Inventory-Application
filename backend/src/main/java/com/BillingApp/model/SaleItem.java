package com.BillingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sale_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;
    private BigDecimal unitPrice = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO ;
    private BigDecimal tax = BigDecimal.ZERO ;
    private BigDecimal subtotal = BigDecimal.ZERO ;
	
    
    public SaleItem() {
		super();
	}
    
    
	public SaleItem(Long id, Sale sale, Item item, int quantity, BigDecimal unitPrice, BigDecimal discount,
			BigDecimal tax, BigDecimal subtotal) {
		super();
		this.id = id;
		this.sale = sale;
		this.item = item;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.tax = tax;
		this.subtotal = subtotal;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getQuantity() {
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


	public BigDecimal getDiscount() {
		return discount;
	}


	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}


	public BigDecimal getTax() {
		return tax;
	}


	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}


	public BigDecimal getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
	
	
    
}

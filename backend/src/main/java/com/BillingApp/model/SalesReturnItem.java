package com.BillingApp.model;


import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "sales_return_items")
@Data @NoArgsConstructor @AllArgsConstructor
public class SalesReturnItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sales_return_id")
    private SalesReturn salesReturn;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer quantity;
    private Double refundAmount;  // amount returned to customer
	
    public SalesReturnItem() {
		super();
	}

	public SalesReturnItem(Long id, SalesReturn salesReturn, Item item, Integer quantity, Double refundAmount) {
		super();
		this.id = id;
		this.salesReturn = salesReturn;
		this.item = item;
		this.quantity = quantity;
		this.refundAmount = refundAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SalesReturn getSalesReturn() {
		return salesReturn;
	}

	public void setSalesReturn(SalesReturn salesReturn) {
		this.salesReturn = salesReturn;
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

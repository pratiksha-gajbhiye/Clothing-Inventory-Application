package com.BillingApp.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales_returns")
@Data @NoArgsConstructor @AllArgsConstructor
public class SalesReturn {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String returnInvoice;          // unique return invoice number
    private LocalDateTime returnDate;
    private BigDecimal amount;     // ✅ explicit amount field

    @ManyToOne
    @JoinColumn(name = "original_sale_id")
    private Sale originalSale;

    @OneToMany(mappedBy = "salesReturn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesReturnItem> items;

    
    public SalesReturn() {
		super();
	}
    
    

	public SalesReturn(Long id, String returnInvoice, LocalDateTime returnDate, Sale originalSale,
			List<SalesReturnItem> items) {
		super();
		this.id = id;
		this.returnInvoice = returnInvoice;
		this.returnDate = returnDate;
		this.originalSale = originalSale;
		this.items = items;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReturnInvoice() {
		return returnInvoice;
	}

	public void setReturnInvoice(String returnInvoice) {
		this.returnInvoice = returnInvoice;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Sale getOriginalSale() {
		return originalSale;
	}

	public void setOriginalSale(Sale originalSale) {
		this.originalSale = originalSale;
	}

	public List<SalesReturnItem> getItems() {
		return items;
	}

	public void setItems(List<SalesReturnItem> items) {
		this.items = items;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	
}

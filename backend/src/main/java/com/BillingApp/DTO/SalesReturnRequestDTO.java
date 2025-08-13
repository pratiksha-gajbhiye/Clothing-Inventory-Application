package com.BillingApp.DTO;


import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class SalesReturnRequestDTO {
    private String returnInvoice;
    private LocalDateTime returnDate;
    private Long originalSaleId;
    private List<SalesReturnItemDTO> items;
	
    
    public SalesReturnRequestDTO() {
		super();
	}


	public SalesReturnRequestDTO(String returnInvoice, LocalDateTime returnDate, Long originalSaleId,
			List<SalesReturnItemDTO> items) {
		super();
		this.returnInvoice = returnInvoice;
		this.returnDate = returnDate;
		this.originalSaleId = originalSaleId;
		this.items = items;
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


	public Long getOriginalSaleId() {
		return originalSaleId;
	}


	public void setOriginalSaleId(Long originalSaleId) {
		this.originalSaleId = originalSaleId;
	}


	public List<SalesReturnItemDTO> getItems() {
		return items;
	}


	public void setItems(List<SalesReturnItemDTO> items) {
		this.items = items;
	}



}

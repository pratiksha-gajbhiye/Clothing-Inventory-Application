package com.BillingApp.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "cash_bank_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashBankEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(name = "account_type")
    private String accountType; // CASH or BANK

    private BigDecimal amount;

    private String description;

    // Optional: link to Sale
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale relatedSale;

    // Optional: link to Purchase
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase relatedPurchase;

    // Optional: link to Expense
    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense relatedExpense;

	public CashBankEntry() {
	}

	public CashBankEntry(Long id, LocalDate date, String accountType, BigDecimal amount, String description,
			Sale relatedSale, Purchase relatedPurchase, Expense relatedExpense) {
		super();
		this.id = id;
		this.date = date;
		this.accountType = accountType;
		this.amount = amount;
		this.description = description;
		this.relatedSale = relatedSale;
		this.relatedPurchase = relatedPurchase;
		this.relatedExpense = relatedExpense;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Sale getRelatedSale() {
		return relatedSale;
	}

	public void setRelatedSale(Sale relatedSale) {
		this.relatedSale = relatedSale;
	}

	public Purchase getRelatedPurchase() {
		return relatedPurchase;
	}

	public void setRelatedPurchase(Purchase relatedPurchase) {
		this.relatedPurchase = relatedPurchase;
	}

	public Expense getRelatedExpense() {
		return relatedExpense;
	}

	public void setRelatedExpense(Expense relatedExpense) {
		this.relatedExpense = relatedExpense;
	}
    
    
}

package com.BillingApp.model;


import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "opening_balance_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningBalancePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    private Double payment;

    private String paymentType;
    private String paymentNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Customer customer;

    
	public OpeningBalancePayment() {
		super();
	}

	public OpeningBalancePayment(Long id, LocalDate paymentDate, Double payment, String paymentType, String paymentNote,
			Customer customer) {
		super();
		this.id = id;
		this.paymentDate = paymentDate;
		this.payment = payment;
		this.paymentType = paymentType;
		this.paymentNote = paymentNote;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}

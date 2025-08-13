package com.BillingApp.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Basic Info
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 15)
    private String mobile;

    @Column(unique = true)
    private String email;

    private String phone;

    private String gstNumber;
    private String taxNumber;

    // Address
    private String country;
    private String state;
    private String city;
    private String postcode;
    private String address;

    // Financials
    @Column(nullable = false)
    private Double previousDue = 0.0;

    // Opening balance payments history
    @OneToMany(
        mappedBy = "customer",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OpeningBalancePayment> openingBalancePayments = new ArrayList<>();

    // Convenience method to add an opening payment
    public void addOpeningBalancePayment(OpeningBalancePayment payment) {
        payment.setCustomer(this);
        openingBalancePayments.add(payment);
    }

	public Customer() {
		super();
	}

	public Customer(Long id, String name, String mobile, String email, String phone, String gstNumber, String taxNumber,
			String country, String state, String city, String postcode, String address, Double previousDue,
			List<OpeningBalancePayment> openingBalancePayments) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.phone = phone;
		this.gstNumber = gstNumber;
		this.taxNumber = taxNumber;
		this.country = country;
		this.state = state;
		this.city = city;
		this.postcode = postcode;
		this.address = address;
		this.previousDue = previousDue;
		this.openingBalancePayments = openingBalancePayments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getPreviousDue() {
		return previousDue;
	}

	public void setPreviousDue(Double previousDue) {
		this.previousDue = previousDue;
	}

	public List<OpeningBalancePayment> getOpeningBalancePayments() {
		return openingBalancePayments;
	}

	public void setOpeningBalancePayments(List<OpeningBalancePayment> openingBalancePayments) {
		this.openingBalancePayments = openingBalancePayments;
	}

	public void setOpeningBalancePayments(BigDecimal openingBalance) {
		// TODO Auto-generated method stub
		
	}

	
    
    
}


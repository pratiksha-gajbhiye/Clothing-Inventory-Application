package com.BillingApp.DTO;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerDTO {
    private String name;
    private String mobile;
    private String email;
    private String phone;
    private String gstNumber;
    private String taxNumber;
    private String country;
    private String state;
    private String city;
    private String postcode;
    private String address;
    private BigDecimal openingBalance;
	
    public CustomerDTO() {
		super();
	}

	public CustomerDTO(String name, String mobile, String email, String phone, String gstNumber, String taxNumber,
			String country, String state, String city, String postcode, String address, BigDecimal openingBalance) {
		super();
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
		this.openingBalance = openingBalance;
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

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}




}

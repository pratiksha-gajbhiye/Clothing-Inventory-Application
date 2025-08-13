package com.BillingApp.DTO;


import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private String name;
    private String skuCode;
    private String barcode;
    private String size;
    private String color;
    private int stock;
    private double costPrice;
    private double sellingPrice;
    private double gstPercentage;
    private Long brandId;
    private Long categoryId;
	
    
    public ItemDTO() {
		super();
	}


	public ItemDTO(Long id, String name, String skuCode, String barcode, String size, String color, int stock,
			double costPrice, double sellingPrice, double gstPercentage, Long brandId, Long categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.skuCode = skuCode;
		this.barcode = barcode;
		this.size = size;
		this.color = color;
		this.stock = stock;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.gstPercentage = gstPercentage;
		this.brandId = brandId;
		this.categoryId = categoryId;
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


	public String getSkuCode() {
		return skuCode;
	}


	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}


	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public double getCostPrice() {
		return costPrice;
	}


	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}


	public double getSellingPrice() {
		return sellingPrice;
	}


	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public double getGstPercentage() {
		return gstPercentage;
	}


	public void setGstPercentage(double gstPercentage) {
		this.gstPercentage = gstPercentage;
	}


	public Long getBrandId() {
		return brandId;
	}


	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


}

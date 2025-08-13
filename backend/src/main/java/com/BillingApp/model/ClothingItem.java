package com.BillingApp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ClothingItem {
    @Id @GeneratedValue
    private Long id;
    private Long categoryId;
    private Long brandId;

    private String name;
    private String gender; // Men, Women, Unisex
    private String imageUrl;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @OneToMany(mappedBy = "clothingItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Variant> variants = new ArrayList<>();

	public ClothingItem() {
	}

	public ClothingItem(Long id, String name, String gender, String imageUrl, Category category, Brand brand,
			List<Variant> variants) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.imageUrl = imageUrl;
		this.category = category;
		this.brand = brand;
		this.variants = variants;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<Variant> getVariants() {
		return variants;
	}

	public void setVariants(List<Variant> variants) {
		this.variants = variants;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	

	
}

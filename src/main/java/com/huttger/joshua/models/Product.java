package com.huttger.joshua.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String description;
	private String name;
	private long price;
	public Product(String name, String description,long price) {
		setDescription(description);
		setName(name);
		setPrice(price);
	}
	public Product() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		this.name = n;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long p) {
		this.price = p;
	}
	@Override
	public boolean equals(Object other) {
		if(other instanceof Product) {
			Product otherProduct = (Product)other;
			return this.getDescription().equals(otherProduct.getDescription()) &&
					this.getName().equals(otherProduct.getName()) &&
					this.getPrice() == otherProduct.getPrice() &&
					this.getId() == otherProduct.getId();
		}else {
			return false;
		}
	}
}

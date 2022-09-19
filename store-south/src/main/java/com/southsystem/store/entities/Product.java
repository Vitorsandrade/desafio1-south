package com.southsystem.store.entities;

import java.util.Objects;

public class Product {

	private Integer id;

	private String name;

	private Double price;

	private Integer amount;

	private String category;
	
	public Product() {
		
	}

	public Product(String name, double price, int amount, String category, int id) {
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.category = category;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Produto ID - " + this.id + "\nNome - " + this.name + "\nCategoria - " + this.category + "\nPreço - R$"
				+ this.price + "\nQuantidade - " + this.amount + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}

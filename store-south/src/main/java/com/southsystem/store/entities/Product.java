package com.southsystem.store.entities;

import java.util.Objects;

public class Product {

	private String name;

	private String category;

	private Double price;

	private Integer amount;

	public Product() {

	}

	public Product(String name, String category, double price, int amount) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.amount = amount;
		

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

	@Override
	public String toString() {
		return "\nProduto:\nNome - " + this.name + "\nCategoria - " + this.category + "\nPreço - R$" + this.price
				+ "\nQuantidade - " + this.amount + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
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
		return Objects.equals(name, other.name);
	}

}

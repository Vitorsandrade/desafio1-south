package store.builders;

import java.math.BigDecimal;

import store.entities.Product;
import store.geradores.Geradores;
import store.services.DataBase;

public class ProductBuilder {

	private String id;

	private String name;
	private BigDecimal price;
	private Integer amount;
	private String category;

	private String barCode;
	private String color;
	private String material;
	private String description;

	public ProductBuilder(String name, String category, BigDecimal price, int amount) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.amount = amount;
	}

	public ProductBuilder id(String id) {
		this.id = id;
		return this;
	}

	public ProductBuilder barCode(String barCode) {
		this.barCode = barCode;
		return this;
	}

	public ProductBuilder description(String description) {
		this.description = description;
		return this;
	}

	public ProductBuilder color(String color) {
		this.color = color;
		return this;
	}

	public ProductBuilder material(String material) {
		this.material = material;
		return this;
	}

	public Product buildProduct() {
		for (Product product : DataBase.instance().recovering().values()) {
			if (!product.getId().equals(id)) {
				id = product.getId();
			}
		}

		this.id = Geradores.gerarId();
		this.barCode = Geradores.gerarCodBar();

		return new Product(id, barCode, description, name, category, price, amount, color, material);
	}

	public Product buildProductModel() {
		return new Product(id, barCode, description, name, category, price, amount, color, material);
	}

}

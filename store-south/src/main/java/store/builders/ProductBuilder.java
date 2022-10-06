package store.builders;

import java.math.BigDecimal;
import java.time.LocalDate;

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
	private LocalDate fabricationDate;
	private LocalDate dateValidity;

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

	public ProductBuilder fabricationDate(LocalDate fabricationDate) {
		this.fabricationDate = fabricationDate;
		return this;
	}

	public ProductBuilder dateValidity(LocalDate dateValidity) {
		this.dateValidity = dateValidity;
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
		this.fabricationDate = LocalDate.now();
		this.dateValidity = LocalDate.now();
		return new Product(id, barCode, description, name, category, price, amount, color, material, fabricationDate,
				dateValidity);
	}

	public Product buildProductModel() {
		return new Product(id, barCode, description, name, category, price, amount, color, material, fabricationDate,
				dateValidity);
	}

}

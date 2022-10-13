package store.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import store.builders.ProductBuilder;
import store.entities.Product;

public class ProductServiceFake {

	public static Boolean postProduct(String name, String category, BigDecimal price, int amount) {

		if (name.isEmpty() && category.isEmpty() && price.signum() >= 0 && amount >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean cancelOrConfirm(String option) {
		if (option.equals("1")) {
			return true;
		} else if (option.equals("2")) {
			return false;
		} else {
			return false;
		}

	}

	public static Boolean searchProduct(String id) {
		List<Product> products = new ArrayList<>();

		products.add(new ProductBuilder("nome", "categoria", BigDecimal.ZERO, 0).id("adt56ggd").buildProductModel());

		for (Product product : products) {
			if (id.equals(product.getId())) {
				return true;
			}
		}
		return false;

	}

}

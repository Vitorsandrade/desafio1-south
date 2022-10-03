package com.southsystem.store.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.southsystem.store.entities.Product;

public class DataBase {

	private static final Map<String, Product> products = new HashMap<String, Product>();

	private static final DataBase dataBase = new DataBase();

	public static DataBase instance() {
		return dataBase;
	}

	public DataBase() {
	}

	public void persistence(Product product) {
		products.put(product.getId(), product);
	}

	public HashMap<String, Product> recovering() {
		return (HashMap<String, Product>) products;
	}

	public void deleteProduct(Product product) {
		products.remove(product.getId());
	}

	public void getAll() {
		System.out.println("\n------           Lista de Produtos           ------\n");
		for (Product product : products.values()) {
			System.out.println(product);
		}
	}

	public void saveOnFile() {
		String path = "src\\main\\resources\\storage.csv";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			bw.write("código, código de barras, descrição, nome, categoria, preço, quantidade, cor, material");
			bw.newLine();
			for (Product product : DataBase.instance().recovering().values()) {
				bw.write(product.toStringFile());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

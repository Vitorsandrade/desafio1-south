package com.southsystem.store.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

	public void readFromShowcase() throws Exception {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean importOK = false;

		do {
			System.out.print("\nDigite o endereço do arquivo .csv que será utilizado: ");
			String mostruario = scan.nextLine();

			int addedProducts = 0;
			int modifiedProducts = 0;

			try (BufferedReader br = new BufferedReader(new FileReader(mostruario))) {
				String line = br.readLine();
				String[] temp;

				while ((line = br.readLine()) != null) {
					temp = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

					BigDecimal margemDeLucro = new BigDecimal("45").divide(new BigDecimal("100"), RoundingMode.CEILING)
							.add(new BigDecimal("1"));

					BigDecimal imposto = new BigDecimal(temp[7].replace(",", ".").replace("\"", ""))
							.divide(new BigDecimal("100"), RoundingMode.CEILING).add(new BigDecimal("1"));

					BigDecimal price = new BigDecimal(temp[6].replace(",", ".").replace("\"", "")).multiply(imposto)
							.multiply(margemDeLucro).setScale(2, RoundingMode.CEILING);

					boolean registerItem = true;

					if (registerItem) {
						ProductService.saveModel(temp[3], price, Integer.parseInt(temp[12]), temp[5], temp[1], temp[0],
								temp[10], temp[4], temp[11]);
						addedProducts++;
					}

				}
				System.out.println("\nMostruário adicionado ao estoque.");
				System.out.println("Produtos adicionados: " + addedProducts);
				System.out.println("Produtos modificados: " + modifiedProducts);

				importOK = true;

			} catch (Exception e) {
				System.out.println("Arquivo inválido"
						+ "\n Para adicionar mostruário, é necessário um arquivo .csv com as seguintes colunas:"
						+ "\ncódigo,codigo de barras,série,nome,descrição,categoria,valor bruto,impostos (%),data de fabricação,data de validade,cor,material");

			}

			DataBase.instance().saveOnFile();

		} while (!importOK);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

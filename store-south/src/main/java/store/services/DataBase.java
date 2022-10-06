package store.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import store.entities.Product;

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

	public void readFile() throws Exception {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		boolean option = false;

		do {
			System.out.println("\nDigite o endereço do arquivo a ser importado ");
			System.out.print("-> ");
			String file = scan.nextLine();

			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line = br.readLine();
				String[] data;

				while ((line = br.readLine()) != null) {
					data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

					BigDecimal price = new BigDecimal(data[6].replace(",", ".").replace("\"", ""));

					DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate date = LocalDate.parse(data[8], fmt1);
					
					LocalDate dateValidity = null;

					if (!data[9].equals("n/a")) {
						dateValidity = LocalDate.parse(data[9], fmt1);
					}

					boolean ok = true;

					if (ok) {

						ProductService.saveModel(data[3], price, Integer.parseInt(data[12]), data[5], data[1], data[0],
								data[10], data[4], data[11], date, dateValidity, data[2]);
					}

				}
				System.out.println("\nPRODUTOS ADICIONADOS COM SUCESSO!");

				option = true;

			} catch (Exception e) {
				System.out.println("ARQUIVO INVÁLIDO!\nAdicione um arquivo existente!");
				e.printStackTrace();

			}

			DataBase.instance().saveOnFile();

		} while (!option);
	}

	public void saveOnFile() {
		String path = "src\\main\\resources\\storage.csv";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			bw.write(
					"código, código de barras, série, nome, descrição, categoria, valor bruto, data de fabricação, cor, material, quantidade");
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

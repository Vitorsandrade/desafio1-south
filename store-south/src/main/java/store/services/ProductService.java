package store.services;

import static store.validations.Validates.validateAmount;
import static store.validations.Validates.validatePrice;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import store.builders.ProductBuilder;
import store.entities.Product;

public class ProductService {

	private static HashMap<String, Product> products;

	public ProductService(HashMap<String, Product> products) {
		ProductService.products = products;

	}

	public static void postProduct() throws IOException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		System.out.println("\n---------------------------------------------------");
		System.out.println("|                    Registro                     |");
		System.out.println("---------------------------------------------------");

		System.out.println("Informe o nome do produto ");
		System.out.print("-> ");
		String name = scan.nextLine();

		boolean add = true;
		for (Product product : products.values()) {
			if (product.getName().equalsIgnoreCase(name)) {
				System.out.println("Produto com o nome " + name + " Já castarado!");
				add = false;
			}
		}

		if (add) {

			while (name.isEmpty()) {
				System.out.println("Informe o nome do produto ");
				System.out.print("-> ");
				name = scan.nextLine();

			}
			String category = "";
			while (category.isEmpty()) {
				System.out.println("Informe a categoria do produto ");
				System.out.print("-> ");
				category = scan.nextLine();
			}

			String descrption = "";
			while (descrption.isEmpty()) {
				System.out.println("Informe a descrição do produto");
				System.out.print("-> ");
				descrption = scan.nextLine();
			}
			String color = "";
			while (color.isEmpty()) {
				System.out.println("Informe a cor do produto");
				System.out.print("-> ");
				color = scan.nextLine();
			}
			String material = "";
			while (material.isEmpty()) {
				System.out.println("Informe o material do produto ");
				System.out.print("-> ");
				material = scan.nextLine();
			}
			BigDecimal price = validatePrice("");

			Integer amount = validateAmount();

			if (cancelOrConfirm()) {
				insertDataBase(name, price, amount, category, color, descrption, material);

				System.out.println("\nPRODUTO ADICIONADO COM SUCESSO!\n");

			} else {
				System.out.println("\nOPERAÇÃO CANCELADA!\n");
			}
		}
		DataBase.instance().saveOnFile();

	}

	public static void putProduct() throws IOException {

		if (products.size() > 1) {
			DataBase.instance().getAll();
		}

		System.out.println("\n---------------------------------------------------");
		System.out.println("|                     Edição                      |");
		System.out.println("---------------------------------------------------");

		if (products.size() > 1) {

			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			Product product = searchProduct();

			boolean verification = false;
			String option = "";

			do {
				System.out.print(
						"Qual dado quer modificar:\n [1]NOME\n [2]CATEGORIA\n [3]PREÇO\n [4]QUANTIDADE\n [5]DESCRIÇÃO\n [6]COR\n [7]MATERIAL\n");
				System.out.print("-> ");
				option = scan.next();

				verification = option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")
						|| option.equals("5") || option.equals("6") || option.equals("7");

			} while (!verification);

			if (cancelOrConfirm(option)) {
				switch (option) {
				case "1":
					scan.nextLine();
					System.out.println("Informe o novo nome ");
					System.out.print("-> ");
					String newName = scan.nextLine();
					product.setName(newName);
					break;

				case "2":
					scan.nextLine();
					System.out.println("Informe a nova categoria ");
					System.out.print("-> ");
					String newCategory = scan.nextLine();
					product.setCategory(newCategory);
					break;

				case "3":
					BigDecimal newPrice = validatePrice("");
					product.setPrice(newPrice);
					break;

				case "4":
					Integer newAmount = validateAmount();
					product.setAmount(newAmount);
					break;

				case "5":
					scan.nextLine();
					System.out.println("Informe a nova descrição ");
					System.out.print("-> ");
					String newDescription = scan.nextLine();
					product.setDescription(newDescription);
					break;

				case "6":
					scan.nextLine();
					System.out.println("Informe a nova cor ");
					System.out.print("-> ");
					String newColor = scan.nextLine();
					product.setColor(newColor);
					break;

				default:
					scan.nextLine();
					System.out.println("Informe o novo material ");
					System.out.print("-> ");
					String newMaterial = scan.nextLine();
					product.setMaterial(newMaterial);
					break;
				}

				System.out.println("\nPRODUTO ALTERADO COM SUCESSO!\n");
			} else
				System.out.println("\nOPERAÇÃO CANCELADA!\n");
		} else
			System.out.println("\nPRIMEIRO PREENCHA A LISTA DE PRODUTOS!\n");

		DataBase.instance().saveOnFile();

	}

	public static void deleteProduct() throws IOException {
		if (products.size() > 1) {
			DataBase.instance().getAll();

			System.out.println("\n---------------------------------------------------");
			System.out.println("|                    Exclusão                     |");
			System.out.println("---------------------------------------------------");

			Product product = searchProduct();

			if (cancelOrConfirm()) {
				DataBase.instance().deleteProduct(product);
				System.out.println("\nPRODUTO EXCLUIDO COM SUCESSO!\n");
			} else {
				System.out.println("\nOPERAÇÃO CANCELADA!\n");
			}
		} else {
			System.out.println("\nPRIMEIRO PREENCHA A LISTA DE PRODUTOS!\n");
		}
		DataBase.instance().saveOnFile();
	}

	public static void importFile() {

		if (cancelOrConfirm()) {
			try {
				DataBase.instance().readFile();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("\nOPERAÇÃO CANCELADA!\n");
		}
		DataBase.instance().getAll();
	}

	public static void insertDataBase(String name, BigDecimal price, Integer amount, String category, String color,
			String description, String material) {
		DataBase.instance().persistence(new ProductBuilder(name, category, price, amount).color(color)
				.description(description).material(material).buildProduct());

	}

	public static void insertDataBase(String name, BigDecimal price, Integer amount, String category, String codBar,
			String id, String color, String description, String material, LocalDate fabricationDate,
			LocalDate dateValidity, String serialNumber) {
		DataBase.instance()
				.persistence(new ProductBuilder(name, category, price, amount).barCode(codBar).id(id).color(color)
						.description(description).material(material).fabricationDate(fabricationDate)
						.dateValidity(dateValidity).serialNumber(serialNumber).buildProductModel());

	}

	public static Boolean cancelOrConfirm() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		System.out.println("Confirmar a operação? [1] para confirmar / [2] para cancelar");
		System.out.print("-> ");
		String cancelOrConfirm = scan.nextLine();

		boolean check = cancelOrConfirm.equals("1") || cancelOrConfirm.equals("2");

		while (!check) {
			System.out.println("OPERAÇÃO INVÁLIDA!");
			System.out.println("[1] para confirmar / [2] para cancelar");
			System.out.print("-> ");
			cancelOrConfirm = scan.nextLine();

			check = cancelOrConfirm.equals("1") || cancelOrConfirm.equals("2");
		}

		if (cancelOrConfirm.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean cancelOrConfirm(String option) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		String returnOption = "";
		switch (option) {
		case "1":
			returnOption = "nome";
			break;
		case "2":
			returnOption = "categoria";
			break;
		case "3":
			returnOption = "preço";
			break;
		case "4":
			returnOption = "quantidade";
			break;
		case "5":
			returnOption = "descrição";
			break;
		case "6":
			returnOption = "cor";
			break;
		default:
			returnOption = "material";
			break;
		}

		System.out.println("Deseja alterar " + returnOption + "? [1] para confirmar / [2] para cancelar");
		System.out.print("-> ");
		String cancelOrConfirm = scan.nextLine();

		boolean check = cancelOrConfirm.equals("1") || cancelOrConfirm.equals("2");

		while (!check) {
			System.out.println("\nOPERAÇÃO INVÁLIDA!\n");
			System.out.print("[1] para confirmar / [2] para cancelar");
			System.out.print("-> ");
			cancelOrConfirm = scan.nextLine();

			check = cancelOrConfirm.equals("1") || cancelOrConfirm.equals("2");
		}

		if (cancelOrConfirm.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	public static Product searchProduct() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		Product productDataBase = null;
		do {
			System.out.println("\nInforme o Id do produto");
			System.out.print("-> ");
			String productId = scan.next();
			for (Product product : DataBase.instance().recovering().values()) {
				if (productId.equalsIgnoreCase(product.getId()) && !product.getId().equals("00000000")) {
					productDataBase = DataBase.instance().recovering().get(productId);
				}
			}
			if (productDataBase == null) {
				System.out.println("PRODUTO COM O ID " + productId + " INVÁLIDO!");
			}

		} while (productDataBase == null);

		System.out.println("\nPRODUTO VÁLIDO: \n" + productDataBase);

		return productDataBase;
	}

	public static BigDecimal finalValue(String taxValue, String grossValue) {

		BigDecimal profitMargin = new BigDecimal("45").divide(new BigDecimal("100")).add(new BigDecimal("1"));

		BigDecimal tax = new BigDecimal(taxValue.replace(",", ".").replace("\"", "")).divide(new BigDecimal("100"))
				.add(new BigDecimal("1"));

		BigDecimal price = new BigDecimal(grossValue.replace(",", ".").replace("\"", "")).multiply(tax)
				.multiply(profitMargin).setScale(2, RoundingMode.CEILING);

		return price;
	}

}

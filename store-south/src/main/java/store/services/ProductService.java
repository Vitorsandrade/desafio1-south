package store.services;

import static store.validacao.Validacoes.validarPreco;
import static store.validacao.Validacoes.validarQuantidade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

import store.builders.ProductBuilder;
import store.entities.Product;

public class ProductService {

	private static HashMap<String, Product> products;

	public ProductService(HashMap<String, Product> products) {
		ProductService.products = products;

	}

	public static void postProduct() {

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
			BigDecimal price = validarPreco("");

			Integer amount = validarQuantidade();

			if (cancelOrConfirm()) {
				insertDataBase(name, price, amount, category, color, descrption, material);

				System.out.println("\nPRODUTO ADICIONADO COM SUCESSO!\n");

			} else {
				System.out.println("OPERAÇÃO CANCELADA!");
			}
		}
		DataBase.instance().saveOnFile();

	}

	public static void putProduct() {

		DataBase.instance().getAll();

		System.out.println("\n---------------------------------------------------");
		System.out.println("|                     Edição                      |");
		System.out.println("---------------------------------------------------");

		if (!products.isEmpty()) {

			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			Product product = searchProduct();

			Product productTemp = product;

			boolean verification = false;
			String option = "";

			do {
				System.out.print("Qual dado quer modificar:\n [1]Nome\n [2]Categoria\n [3]preço\n [4]quantidade\n ");
				System.out.print("-> ");
				option = scan.next();

				verification = option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4");

			} while (!verification);

			switch (option) {
			case "1":
				scan.nextLine();
				System.out.println("Digite o novo nome ");
				System.out.print("-> ");
				String newName = scan.nextLine();
				product.setName(newName);
				break;

			case "2":
				scan.nextLine();
				System.out.println("Digite a nova categoria ");
				System.out.print("-> ");
				String newCategory = scan.nextLine();
				product.setCategory(newCategory);

				break;

			case "3":
				BigDecimal newPrice = validarPreco("");
				product.setPrice(newPrice);

				break;

			case "4":
				Integer newAmount = validarQuantidade();
				product.setAmount(newAmount);

				break;
			default:

			}

			if (cancelOrConfirm()) {
				System.out.println("PRODUTO EDITADO COM SUCESSO!");

			} else {
				switch (option) {
				case "1":
					product.setName(productTemp.getName());
					break;

				case "2":
					product.setPrice(productTemp.getPrice());
					break;

				case "3":
					product.setAmount(productTemp.getAmount());
					break;

				default:
					product.setCategory(productTemp.getCategory());
				}
				System.out.println("OPERAÇÃO CANCELADA!");
			}
		} else {
			System.out.println("Lista ainda não tem produtos");
		}

		DataBase.instance().saveOnFile();
	}

	public static void deleteProduct() {
		if (products.size() >= 2) {
			DataBase.instance().getAll();
			Product product = searchProduct();

			System.out.println("\n---------------------------------------------------");
			System.out.println("|                    Exclusão                     |");
			System.out.println("---------------------------------------------------");

			if (cancelOrConfirm()) {
				DataBase.instance().deleteProduct(product);
				System.out.println("PRODUTO EXCLUIDO COM SUCESSO!");
			} else {
				System.out.println("OPERAÇÃO CANCELADA!");
			}
		} else {
			System.out.println("PRIMEIRO PREENCHA A LISTA DE PRODUTOS!");
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
			System.out.println("Operação cancelada");
		}
		DataBase.instance().getAll();
	}

	public static void insertDataBase(String name, BigDecimal price, Integer amount, String category, String color,
			String description, String material) {
		DataBase.instance().persistence(new ProductBuilder(name, category, price, amount).color(color)
				.description(description).material(material).buildProduct());

	}

	public static void saveModel(String name, BigDecimal price, Integer amount, String category, String codBar,
			String id, String color, String description, String material) {
		DataBase.instance().persistence(new ProductBuilder(name, category, price, amount).barCode(codBar).id(id)
				.color(color).description(description).material(material).buildProductModel());

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

}

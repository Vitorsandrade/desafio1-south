																																												package com.southsystem.store.services;

import static com.southsystem.store.validators.Validators.cancelOrConfirm;
import static com.southsystem.store.validators.Validators.insertDataBase;
import static com.southsystem.store.validators.Validators.searchProduct;
import static com.southsystem.store.validators.Validators.validarPreco;
import static com.southsystem.store.validators.Validators.validarQuantidade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

import com.southsystem.store.entities.Product;

public class ProductService {

	private static HashMap<String, Product> products;

	public ProductService(HashMap<String, Product> products) {
		ProductService.products = products;

	}

	public static void postProduct(){

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		System.out.println("\n---------------------------------------------------");
		System.out.println("|                    Registro                     |");
		System.out.println("---------------------------------------------------");

		System.out.println("Digite o nome do produto ");
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
				System.out.println("Digite o nome do produto ");
				System.out.print("-> ");
				name = scan.nextLine();

			}
			System.out.println("Digite a categoria do produto ");
			System.out.print("-> ");
			String category = scan.nextLine();

			BigDecimal price = validarPreco("");

			Integer amount = validarQuantidade();

			if (cancelOrConfirm()) {
				insertDataBase(name, price, amount, category);

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
				System.out.println("Operação cancelada");
			}
		} else {
			System.out.println("Lista ainda não tem produtos");
		}

		DataBase.instance().saveOnFile();
	}
//
//	public void deleteProduct() {
//		System.out.println("\n---------------------------------------------------");
//		System.out.println("|                    Exclusão                     |");
//		System.out.println("---------------------------------------------------");
//
//		try {
//			System.out.println("Digite o Nome do produto no qual deseja excluir");
//			System.out.print("-> ");
//			String exc = sc.nextLine();
//
//			int index = 0;
//			for (Product product : products) {
//				if (product.getName().toUpperCase().equals(exc.toUpperCase())) {
//					index = products.indexOf(product);
//				}
//			}
//
//			if (index != 0) {
//				products.remove(index);
//				System.out.println("\nProduto excluido com sucesso!\n");
//			} else {
//				System.out.println("\nNOME INVÁLIDO\n");
//			}
//
//		} catch (InputMismatchException e) {
//			System.out.println("\nNOME INVÁLIDO\n");
//		} catch (NumberFormatException e) {
//			System.out.println("\nNOME INVÁLIDO\n");
//		}
//	}
//
//	public void importShowcase() {
//		System.out.println("\n---------------------------------------------------");
//		System.out.println("|                   importação                    |");
//		System.out.println("---------------------------------------------------");
//		System.out.println("\nDigite o caminho do arquivo .csv");
//		System.out.print("-> ");
//		String path = sc.nextLine();
//
//		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//
//			String line = br.readLine();
//
//			while (line != null) {
//				String[] vect = line.split(",");
//				products.add(new Product(vect[0], vect[1], vect[2], Integer.parseInt(vect[3])));
//
//				line = br.readLine();
//			}
//			System.out.println("\nArquivo importado com sucesso!\n");
//
//		} catch (FileNotFoundException e) {
//			System.out.println("\nArquivo não encontrado\n");
//
//		} catch (IOException e) {
//			System.out.println("\nArquivo não encontrado\n");
//		}
//
//	}
//

}

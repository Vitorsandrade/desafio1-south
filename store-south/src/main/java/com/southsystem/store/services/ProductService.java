package com.southsystem.store.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.southsystem.store.entities.Product;

public class ProductService {

	Scanner sc = new Scanner(System.in);

	private List<Product> products = new ArrayList<Product>();

	public void addProduct() {
		System.out.println("\n---------------------------------------------------");
		System.out.println("|                    Registro                     |");
		System.out.println("---------------------------------------------------");

		System.out.println("Digite o nome do produto ");
		System.out.print("-> ");
		String name = sc.nextLine();

		System.out.println("Digite a categoria do produto ");
		System.out.print("-> ");
		String category = sc.nextLine();

		System.out.println("Digite a sua quantidade ");
		System.out.print("-> ");
		String amount = sc.nextLine();

		System.out.println("Digite o preço do produto ");
		System.out.print("-> ");
		String price = sc.nextLine();

		try {
			products.add(new Product(name, category, Double.parseDouble(price), Integer.parseInt(amount)));
			System.out.println("\nProduto registrado com sucesso!\n");

		} catch (NumberFormatException e) {
			System.out.println("\nDigite um campo válido no Preço/Quantidade!!\n");
		}

	}

	public void putProduct() {

		System.out.println("\n---------------------------------------------------");
		System.out.println("|                     Edição                      |");
		System.out.println("---------------------------------------------------");

		try {
			System.out.println("Digite o Nome do produto no qual deseja editar");
			System.out.print("-> ");
			String ident = sc.nextLine();

			products.forEach(product -> {
				if ((product.getName().toUpperCase()).equals(ident.toUpperCase()) && product.getName() != "MODELO") {

					System.out.println("Novo nome");
					System.out.print("-> ");
					String newName = sc.nextLine();

					System.out.println("Nova Categoria ");
					System.out.print("-> ");
					String newCategory = sc.nextLine();

					System.out.println("Novo Preço ");
					System.out.print("-> ");
					String newPrice = sc.nextLine();
					Double.parseDouble(newPrice);

					System.out.println("Nova Quantidade ");
					System.out.print("-> ");
					String newAmount = sc.nextLine();
					Integer.parseInt(newAmount);

					try {
						product.setName(newName);
						product.setCategory(newCategory);
						product.setPrice(Double.parseDouble(newPrice));
						product.setAmount(Integer.parseInt(newAmount));

						System.out.println("\nProduto editado com sucesso!\n");
					} catch (NumberFormatException e) {
						System.out.println("\nInsira um campo válido no Preço/Quantidade!!\n");
					}
				}

			});

		} catch (NumberFormatException e) {
			System.out.println("\nInsira um campo válido\n");
		}

	}

	public void deleteProduct() {
		System.out.println("\n---------------------------------------------------");
		System.out.println("|                    Exclusão                     |");
		System.out.println("---------------------------------------------------");

		try {
			System.out.println("Digite o Nome do produto no qual deseja excluir");
			System.out.print("-> ");
			String exc = sc.nextLine();

			int index = 0;
			for (Product product : products) {
				if (product.getName().toUpperCase().equals(exc.toUpperCase())) {
					index = products.indexOf(product);
				}
			}

			if (index != 0) {
				products.remove(index);
				System.out.println("\nProduto excluido com sucesso!\n");
			} else {
				System.out.println("\nNOME INVÁLIDO\n");
			}

		} catch (InputMismatchException e) {
			System.out.println("\nNOME INVÁLIDO\n");
		} catch (NumberFormatException e) {
			System.out.println("\nNOME INVÁLIDO\n");
		}
	}

	public void importShowcase() {
		System.out.println("\n---------------------------------------------------");
		System.out.println("|                   importação                    |");
		System.out.println("---------------------------------------------------");
		System.out.println("\nDigite o caminho do arquivo .csv");
		System.out.print("-> ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();

			while (line != null) {
				String[] vect = line.split(",");
				products.add(new Product(vect[0], vect[1], Double.parseDouble(vect[2]), Integer.parseInt(vect[3])));

				line = br.readLine();
			}
			System.out.println("\nArquivo importado com sucesso!\n");

		} catch (FileNotFoundException e) {
			System.out.println("\nArquivo não encontrado\n");

		} catch (IOException e) {
			System.out.println("\nArquivo não encontrado\n");
		}

	}

	public void getAll() {
		System.out.println("\n------           Lista de Produtos           ------\n");

		products.forEach(System.out::println);
	}

	public char cancelOption() {
		System.out.println("Deseja cancelar operação e voltar ao menu? [S/N]");
		System.out.print("-> ");
		char option = sc.next().charAt(0);
		sc.nextLine();

		return option;
	}

	public void registerModel(String model, String category, double price, int amount) {
		products.add(new Product(model, category, price, amount));
	}

}

package com.southsystem.store.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.southsystem.store.entities.Product;

public class ProductService {

	public static Boolean apresentacao(boolean d) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date data = new Date();
		if (d == false) {
			System.out.println("        Inicio do programa: " + format.format(data));
		} else {
			System.out.println("\n          Fim do programa: " + format.format(data));
		}
		System.out.println("\n     ************** South Store **************\n");
		return d;
	}

	public static String menu() {
		Scanner sc = new Scanner(System.in);
		String option;
		System.out.println("---------------------------------------------------");
		System.out.println("|      DIGITE O NÚMERO DA OPÇÃO QUE DESEJA        |");
		System.out.println("---------------------------------------------------");
		System.out.println("|            1 - Registrar produto                |");
		System.out.println("|            2 - Editar produto                   |");
		System.out.println("|            3 - Excluir produto                  |");
		System.out.println("|            4 - Importar produtos                |");
		System.out.println("|            5 - Listar produtos                  |");
		System.out.println("|            6 - Sair                             |");
		System.out.println("---------------------------------------------------");
		System.out.print("-> ");

		option = sc.nextLine();
		return option;
	}

	public static void cod() {
		Scanner sc = new Scanner(System.in);
		int fim = 0;
		int i = 0;
		int id;
		List<Product> produtos = new ArrayList<>();

		produtos.add(new Product("Modelo", 0000, 0000, "Modelo", i));
		i++;
		do {
			switch (menu()) {
			case "1":
				// OK

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
				int amount = sc.nextInt();

				System.out.println("Digite o preço do produto ");
				System.out.print("-> ");
				double price = sc.nextDouble();

				sc.nextLine();

				id = i;

				produtos.add(new Product(name, price, amount, category, id));
				System.out.println("\nProduto reistrado com sucesso!\n");

				i++;

				fim = 1;
				break;

			case "2":
				// OK
				System.out.println("\n------           Lista de Produtos           ------\n");

				produtos.forEach(System.out::println);

				System.out.println("\n---------------------------------------------------");
				System.out.println("|                     Edição                      |");
				System.out.println("---------------------------------------------------");
				System.out.println("Deseja cancelar operação? [S/N]");
				System.out.println("-> ");
				char opcao = sc.next().charAt(0);
				sc.nextLine();
				
				if (opcao == 'S' || opcao == 's') {
					break;
				}
				System.out.println("Digite o ID do produto no qual deseja editar");
				System.out.print("-> ");
				String ident = sc.nextLine();
				
				produtos.forEach(product -> {
					if (product.getId().equals(Integer.parseInt(ident)) && product.getId()!= 0) {

						System.out.println("Novo nome");
						System.out.print("-> ");
						String novoNome = sc.nextLine();

						System.out.println("Nova Categoria ");
						System.out.print("-> ");
						String novaCategoria = sc.nextLine();

						System.out.println("Novo Preço ");
						System.out.print("-> ");
						double novoPreco = sc.nextDouble();

						System.out.println("Nova Quantidade ");
						System.out.print("-> ");
						int novaQuantia = sc.nextInt();
						sc.nextLine();

						product.setName(novoNome);
						product.setCategory(novaCategoria);
						product.setPrice(novoPreco);
						product.setAmount(novaQuantia);

						System.out.println("\nProduto editado com sucesso!\n");
					} else {
						System.out.println("\nProduto inválido!\n");
					}
				});

				fim = 2;
				break;

			case "3":

				System.out.println("\n---------------------------------------------------");
				System.out.println("|                    Exclusão                     |");
				System.out.println("---------------------------------------------------");
				System.out.println("\n------           Lista de Produtos           ------\n");
				produtos.forEach(System.out::println);
				try {
					System.out.println("Digite o ID do produto no qual deseja excluir");
					System.out.print("-> ");
					String exc = sc.nextLine();

					int index = 0;
					for (Product product : produtos) {
						if (product.getId().equals(Integer.parseInt(exc))) {
							index = produtos.indexOf(product);
						}
					}

					if (index != 0) {
						produtos.remove(index);
						System.out.println("\nProduto excluido com sucesso!\n");
					} else {
						System.out.println("\nID INVÁLIDO\n");
					}
				} catch (InputMismatchException e) {
					System.out.println("\nID INVÁLIDO\n");
				} catch (NumberFormatException e) {
					System.out.println("\nID INVÁLIDO\n");
				}
				fim = 3;
				break;

			case "4":
				// OK
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
						int idImp = i;
						produtos.add(new Product(vect[0], Double.parseDouble(vect[1]), Integer.parseInt(vect[2]),
								vect[3], idImp));

						i++;

						line = br.readLine();
					}
					System.out.println("\nArquivo impotado com sucesso!\n");

				} catch (FileNotFoundException e) {
					System.out.println("\n" + e.getMessage() + "\n");

				} catch (IOException e) {
					System.out.println("\n" + e.getMessage() + "\n");
				}
				fim = 4;
				break;

			case "5":
				// OK
				System.out.println("\n------           Lista de Produtos           ------\n");
				if (!produtos.isEmpty()) {
					produtos.forEach(System.out::println);
				} else {
					System.out.println("\n    #######         LISTA VAZIA!        #######\n");
				}
				fim = 5;

				break;

			case "6":
				fim = 6;
				break;
			default:
				System.out.println("\nDigite um número correspondente a operação desejada!\n");
				break;
			}

		} while (fim < 6);

	}
}

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
import com.southsystem.store.services.ProductService2;

public class  ProductService2 {


	
//	public static Boolean apresentacao(boolean d) {
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm");
//		Date data = new Date();
//
//		if (d == false) {
//			System.out.println("        Inicio do programa: " + format.format(data));
//		} else {
//			System.out.println("\n          Fim do programa: " + format.format(data));
//		}
//
//		System.out.println("\n     ************** South Store **************\n");
//		return d;
//	}
//
//	
//	public static String menu() {
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("---------------------------------------------------");
//		System.out.println("|      DIGITE O NÚMERO DA OPÇÃO QUE DESEJA        |");
//		System.out.println("---------------------------------------------------");
//		System.out.println("|            1 - Registrar produto                |");
//		System.out.println("|            2 - Editar produto                   |");
//		System.out.println("|            3 - Excluir produto                  |");
//		System.out.println("|            4 - Importar produtos                |");
//		System.out.println("|            5 - Listar produtos                  |");
//		System.out.println("|            6 - Sair                             |");
//		System.out.println("---------------------------------------------------");
//		System.out.print("-> ");
//		String option = sc.nextLine();
//
//		return option;
//
//	}
//	
//	public static void cod() {
//		Scanner sc = new Scanner(System.in);
//
//		List<Product> produtos = new ArrayList<>();
//
//		
//		int fim = 0;
//		int index = 0;
//
//		produtos.add(new Product("Modelo", 0000, 0000, "Modelo"));
//			
//		do {
//			switch (menu()) {
//			case "1":
////				System.out.println("\n---------------------------------------------------");
////				System.out.println("|                    Registro                     |");
////				System.out.println("---------------------------------------------------");
////
////				System.out.println("Digite o nome do produto ");
////				System.out.print("-> ");
////				String name = sc.nextLine();
////
////				System.out.println("Digite a categoria do produto ");
////				System.out.print("-> ");
////				String category = sc.nextLine();
////
////				System.out.println("Digite a sua quantidade ");
////				System.out.print("-> ");
////				String amount = sc.nextLine();
////
////				System.out.println("Digite o preço do produto ");
////				System.out.print("-> ");
////				String price = sc.nextLine();
////				
////				try {
////					produtos.add(new Product(name, Double.parseDouble(price), Integer.parseInt(amount), category));
////					System.out.println("\nProduto reistrado com sucesso!\n");
////
////				} catch (NumberFormatException e) {
////					System.out.println("\nDigite um campo válido no Preço/Quantidade!!\n");
////				}
//
//			ProductController.registrar();
//
//				fim = 1;
//				break;
//
//			case "2":
////				System.out.println("\n------           Lista de Produtos           ------\n");
////
////				produtos.forEach(System.out::println);
////
////				System.out.println("\n---------------------------------------------------");
////				System.out.println("|                     Edição                      |");
////				System.out.println("---------------------------------------------------");
////				System.out.println("Deseja cancelar operação e voltar ao menu? [S/N]");
////				System.out.print("-> ");
////				char opcao = sc.next().charAt(0);
////				sc.nextLine();
////
////				if (opcao == 'S' || opcao == 's') {
////					System.out.println("\nOperação Cancelada!\n");
////					break;
////				}
////
////				try {
////				System.out.println("Digite o Nome do produto no qual deseja editar");
////				System.out.print("-> ");
////				String ident = sc.nextLine();
////				
////				
////				produtos.forEach(product -> {
////					if ((product.getName().toUpperCase()).equals(ident.toUpperCase()) && product.getName() != "Modelo") {
////
////						System.out.println("Novo nome");
////						System.out.print("-> ");
////						String novoNome = sc.nextLine();
////
////						System.out.println("Nova Categoria ");
////						System.out.print("-> ");
////						String novaCategoria = sc.nextLine();
////
////						System.out.println("Novo Preço ");
////						System.out.print("-> ");
////						String novoPreco = sc.nextLine();
////						Double.parseDouble(novoPreco);
////
////						System.out.println("Nova Quantidade ");
////						System.out.print("-> ");
////						String novaQuantia = sc.nextLine();
////						Integer.parseInt(novaQuantia);
////						
////						try {
////							product.setName(novoNome);
////							product.setCategory(novaCategoria);
////							product.setPrice(Double.parseDouble(novoPreco));
////							product.setAmount(Integer.parseInt(novaQuantia));
////
////							System.out.println("\nProduto editado com sucesso!\n");
////						} catch (NumberFormatException e) {
////							System.out.println("\nInsira um campo válido no Preço/Quantidade!!\n");
////						}
////					}
////				});
////
////				}catch (NumberFormatException e) {
////					System.out.println("\nInsira um campo válido\n");
////				}
//
//				ProductController.editar();
//				fim = 2;
//				break;
//
//			case "3":
////				System.out.println("\n---------------------------------------------------");
////				System.out.println("|                    Exclusão                     |");
////				System.out.println("---------------------------------------------------");
////				System.out.println("\n------           Lista de Produtos           ------\n");
////
////				produtos.forEach(System.out::println);
////
////				try {
////					System.out.println("Digite o Nome do produto no qual deseja excluir");
////					System.out.print("-> ");
////					String exc = sc.nextLine();
////
////					for (Product product : produtos) {
////						if (product.getName().toUpperCase().equals(exc.toUpperCase())) {
////							index = produtos.indexOf(product);
////						}
////					}
////
////					if (index != 0) {
////						produtos.remove(index);
////						System.out.println("\nProduto excluido com sucesso!\n");
////					} else {
////						System.out.println("\nNOME INVÁLIDO\n");
////					}
////
////				} catch (InputMismatchException e) {
////					System.out.println("\nNOME INVÁLIDO\n");
////				} catch (NumberFormatException e) {
////					System.out.println("\nNOME INVÁLIDO\n");
////				}
//
//				ProductController.listar();
//				ProductController.excluir();
//				fim = 3;
//				break;
//
//			case "4":
////				System.out.println("\n---------------------------------------------------");
////				System.out.println("|                   importação                    |");
////				System.out.println("---------------------------------------------------");
////				System.out.println("\nDigite o caminho do arquivo .csv");
////				System.out.print("-> ");
////				String path = sc.nextLine();
////
////				try (BufferedReader br = new BufferedReader(new FileReader(path))) {
////
////					String line = br.readLine();
////
////					while (line != null) {
////						String[] vect = line.split(",");
////						produtos.add(new Product(vect[0], Double.parseDouble(vect[1]), Integer.parseInt(vect[2]),
////								vect[3]));
////
////						
////
////						line = br.readLine();
////					}
////					System.out.println("\nArquivo importado com sucesso!\n");
////
////				} catch (FileNotFoundException e) {
////					System.out.println("\nArquivo não encontrado\n");
////
////				} catch (IOException e) {
////					System.out.println("\nArquivo não encontrado\n");				}
//				
//				ProductController.importar();
//				fim = 4;
//				break;
//
//			case "5":
////				System.out.println("\n------           Lista de Produtos           ------\n");
////
////				produtos.forEach(System.out::println);
//
//				ProductController.listar();
//				fim = 5;
//				break;
//
//			case "6":
//				fim = 6;
//				break;
//
//			default:
//				System.out.println("\nInsira um número correspondente a operação desejada!\n");
//				break;
//
//			}
//
//		} while (fim != 6);
//		sc.close();
//	
//
//	}
}

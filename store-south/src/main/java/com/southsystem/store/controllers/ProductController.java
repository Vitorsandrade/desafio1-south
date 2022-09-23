package com.southsystem.store.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.southsystem.store.services.ProductService;

public class ProductController {

	public static Boolean presentation(boolean d) {
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
		String option = sc.nextLine();

		return option;

	}

	public static void start() {
		Scanner sc = new Scanner(System.in);

		ProductService productService = new ProductService();
		
		productService.registerModel("MODELO","MODELO", 0, 0 );

		int fim = 0;
		char opcao;

		do {
			switch (menu()) {
			case "1":

				opcao = productService.cancelOption();

				if (opcao == 'S' || opcao == 's') {
					System.out.println("\nOperação Cancelada!\n");
					break;
				} else if (opcao == 'n' || opcao == 'N') {
					productService.addProduct();
				} else
					System.out.println("\nCaracter Inválido!!\n");

				fim = 1;
				break;

			case "2":
				opcao = productService.cancelOption();

				if (opcao == 'S' || opcao == 's') {
					System.out.println("\nOperação Cancelada!\n");
					break;
				} else if (opcao == 'n' || opcao == 'N') {
					productService.getAll();
					productService.putProduct();
				} else
					System.out.println("\nCaracter Inválido!!\n");

				fim = 2;
				break;

			case "3":

				opcao = productService.cancelOption();

				if (opcao == 'S' || opcao == 's') {
					System.out.println("\nOperação Cancelada!\n");
					break;
				} else if (opcao == 'n' || opcao == 'N') {
					productService.getAll();
					productService.deleteProduct();
				} else
					System.out.println("\nCaracter Inválido!!\n");

				fim = 3;
				break;

			case "4":

				opcao = productService.cancelOption();

				if (opcao == 'S' || opcao == 's') {
					System.out.println("\nOperação Cancelada!\n");
					break;
				} else if (opcao == 'n' || opcao == 'N') {
					productService.importShowcase();
				} else
					System.out.println("\nCaracter Inválido!!\n");

				fim = 4;
				break;

			case "5":

				productService.getAll();

				fim = 5;
				break;

			case "6":

				fim = 6;
				break;

			default:
				System.out.println("\nInsira um número correspondente a operação desejada!\n");
				break;

			}

		} while (fim != 6);
		sc.close();

	}
}

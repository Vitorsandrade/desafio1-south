package store.controllers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import store.entities.Product;
import store.services.DataBase;
import store.services.ProductService;

public class ProductController {

	HashMap<String, Product> products;
	ProductService productService;

	public ProductController() {
		this.products = DataBase.instance().recovering();
		this.productService = new ProductService(products);
	}

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
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

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
		String option = scan.nextLine();

		return option;
	}

	public void start() {
		Scanner scan = new Scanner(System.in);

		ProductService.saveModel("MODELO", BigDecimal.ZERO, 0, "MODELO", "000000000000", "00000000", "MODELO", "MODELO",
				"MODELO", LocalDate.now(), LocalDate.now(), "00/0000");

		int fim = 0;

		do {
			switch (menu()) {
			case "1":

				ProductService.postProduct();

				fim = 1;
				break;

			case "2":

				ProductService.putProduct();

				fim = 2;
				break;

			case "3":

				ProductService.deleteProduct();

				fim = 3;
				break;

			case "4":

				ProductService.importFile();

				fim = 4;
				break;

			case "5":

				DataBase.instance().getAll();

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
		scan.close();

	}
}

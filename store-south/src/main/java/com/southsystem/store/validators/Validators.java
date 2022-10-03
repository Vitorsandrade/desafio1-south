package com.southsystem.store.validators;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.southsystem.store.builders.ProductBuilder;
import com.southsystem.store.entities.Product;
import com.southsystem.store.services.DataBase;

public class Validators {

	public static void saveModel(String name, BigDecimal price, Integer amount, String category, String codBar,
			String id, String color, String description, String material) {
		DataBase.instance().persistence(new ProductBuilder(name, category, price, amount).barCode(codBar).id(id)
				.color(color).description(description).material(material).buildProductModel());
	}

	public static void insertDataBase(String name, BigDecimal price, Integer amount, String category) {
		DataBase.instance().persistence(new ProductBuilder(name, category, price, amount).buildProduct());

		System.out.println("\nPRODUTO ADICIONADO COM SUCESSO! \n");
	}

	public static Integer validarQuantidade() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		boolean option = true;
		int temp = 0;

		do {
			try {
				System.out.println("Quantidade ");
				System.out.print("-> ");
				temp = scan.nextInt();

				option = false;

				if (temp < 0) {
					throw new Exception();
				}
			} catch (InputMismatchException e) {
				System.out.println("INSIRA NÚMEROS POSITIVOS!");
				scan.nextLine();

			} catch (Exception e) {
				System.out.println("INSIRA NÚMEROS POSITIVOS!");
				option = true;
			}
		} while (option);

		return temp;
	}

	public static BigDecimal validarPreco(String valor) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		BigDecimal temp = null;
		boolean option = true;

		do {
			try {
				System.out.println("Preço ");
				System.out.print("-> ");
				valor = scan.nextLine();

				while (valor.equals("")) {
					System.out.println("Digite um valor válido.");
					System.out.println("Preço ");
					System.out.print("-> ");
					valor = scan.nextLine();
				}

				temp = new BigDecimal(valor.replace(",", "."));

				if (temp.signum() < 0) {
					throw new Exception();
				}
				option = false;
			} catch (NumberFormatException e) {
				System.out.println("INSIRA APENAS VALORES NÚMERICOS PARA O PREÇO! ");

			} catch (Exception e) {
				System.out.println("VALOR DO PRODUTO DEVE SER POSITIVO! ");
				option = true;
			}
		} while (option);

		return temp;
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

}

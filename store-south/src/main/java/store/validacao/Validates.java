package store.validacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validates {

	public static Integer validateAmount() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		boolean option = true;
		int amountTemp = 0;

		do {
			try {
				System.out.println("Quantidade ");
				System.out.print("-> ");
				amountTemp = scan.nextInt();

				option = false;

				if (amountTemp < 0) {
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

		return amountTemp;
	}

	public static BigDecimal validatePrice(String valor) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		BigDecimal priceTemp = null;
		boolean option = true;

		do {
			try {
				System.out.println("Preço ");
				System.out.print("-> ");
				valor = scan.nextLine();

				while (valor.equals("")) {
					System.out.println("Informe um valor válido.");
					System.out.println("Preço ");
					System.out.print("-> ");
					valor = scan.nextLine();
				}

				priceTemp = new BigDecimal(valor.replace(",", "."));

				if (priceTemp.signum() < 0) {
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

		return priceTemp;
	}

	public static String validateDate(LocalDate date) {
		if (date == null) {
			return "n/a";
		} else {

			return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}

	}
}

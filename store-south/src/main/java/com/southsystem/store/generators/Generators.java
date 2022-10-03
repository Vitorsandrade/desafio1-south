package com.southsystem.store.generators;

import java.util.Random;

public class Generators {

	public static String returnId() {
		String characters = "abcdefghijkmnopqrstuvwxyz023456789";
		String randomString = "";
		int length = 8;

		Random rand = new Random();

		char[] text = new char[length];

		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}

		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}

		return randomString;

	}

	public static String returnCodBar() {

		String characters = "0123456789";
		String randomString = "";
		int length = 12;

		Random rand = new Random();

		char[] text = new char[length];

		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rand.nextInt(characters.length()));
		}

		for (int i = 0; i < text.length; i++) {
			randomString += text[i];
		}

		return randomString;
	}
}

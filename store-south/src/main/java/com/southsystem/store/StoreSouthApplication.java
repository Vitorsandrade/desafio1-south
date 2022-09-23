package com.southsystem.store;

import static com.southsystem.store.controllers.ProductController.presentation;
import static com.southsystem.store.controllers.ProductController.start;

public class StoreSouthApplication {

	public static void main(String[] args) {

		presentation(false);
		start();
		presentation(true);
	}

}

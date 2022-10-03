package com.southsystem.store;

import static com.southsystem.store.controllers.ProductController.presentation;

import com.southsystem.store.controllers.ProductController;

public class StoreSouthApplication {

	public static void main(String[] args) {

		ProductController productController = new ProductController();

		presentation(false);
		productController.start();
		presentation(true);
	}

}

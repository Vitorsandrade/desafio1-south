package store.program;

import static store.controllers.ProductController.presentation;

import java.io.IOException;

import org.springframework.boot.SpringApplication;

import store.controllers.ProductController;

public class StoreSouthApplication {

	public static void main(String[] args) throws IOException {

		ProductController productController = new ProductController();

		SpringApplication.run(StoreSouthApplication.class, args);
		presentation(false);
		productController.start();
		presentation(true);
	}

}

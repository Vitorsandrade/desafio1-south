package store.program;

import store.controllers.ProductController;
import static store.controllers.ProductController.presentation;

public class StoreSouthApplication {

	public static void main(String[] args) {
		
		ProductController productController = new ProductController();

		presentation(false);
		productController.start();
		presentation(true);
	}

}

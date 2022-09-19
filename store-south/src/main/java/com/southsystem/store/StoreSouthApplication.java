package com.southsystem.store;

import com.southsystem.store.services.ProductService;

public class StoreSouthApplication {

	public static void main(String[] args) {

		ProductService.apresentacao(false);
		ProductService.cod();
		ProductService.apresentacao(true);
	}

}

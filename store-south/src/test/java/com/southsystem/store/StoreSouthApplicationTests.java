package com.southsystem.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.southsystem.store.entities.Product;


class StoreSouthApplicationTests {

	@Test
	public void testandoClasse() {
		Product prod = new Product("test", 0, 0, "test", 0);
		
		prod.setAmount(10);
		Assertions.assertTrue(prod.getAmount() == 10);
		
	}

}

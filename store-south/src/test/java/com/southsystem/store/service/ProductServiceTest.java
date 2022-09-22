package com.southsystem.store.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {

	@Test
	public void test() {
		//passa o valor true
		Assertions.assertTrue(true);
		
		//passa o valor false
		Assertions.assertFalse(false);
		
		//compara se um valor é igual ao outro
		Assertions.assertEquals(1, 1);
		
		//cmpara se não são iguais
//		Assertions.assertNotEquals(null, null);
		
		//idem float 
		Assertions.assertEquals(0.51, 0.51);
		
		int i = 5;
		Integer i2 = 5;
		
		// não tem como fazer o unboxing/boxing de forma automatica como o java
		Assertions.assertEquals(Integer.valueOf(i), i2);
		
		Assertions.assertEquals(i, i2.intValue());
		
		//comparando Strings
		Assertions.assertEquals("bola", "bola");
		
		//Compara independente de letra masicula ou minuscula
		Assertions.assertTrue("bola".equalsIgnoreCase("Bola"));
	}
}

package store.validations;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ValidatesTest {

	@Test
	void testValidateAmount() {
		int amount = Validates.validateAmount();
		assertEquals(1, amount);
	}

	@Test
	void testValidatePrice() {
		BigDecimal price = Validates.validatePrice("1");
		assertEquals(BigDecimal.ONE, price);
	}

	@Test
	public void pegarDadosProdutos() {
		
	}
//	@Test
//	void testValidateDate() {
//		fail("Not yet implemented");
//	}

}

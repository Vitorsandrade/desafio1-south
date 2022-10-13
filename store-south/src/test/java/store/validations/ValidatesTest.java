package store.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class ValidatesTest {

	@Test
	public void testNullDateInput() {
		String test = Validates.validateDate(null);
		assertEquals("n/a", test);
	}

	@Test
	public void testValidDateInput() {
		LocalDate date = LocalDate.now();
		String test = Validates.validateDate(date);

		assertEquals(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), test);
	}

	@Test
	public void testIfItReceivesPositiveValueOrZeroReturnsTrueOfTheValidateAmount() {
		Boolean test = ValidatesFake.validateAmount(1);
		assertTrue(test);
	}

	@Test
	public void testIfItReceivesNegativeValueReturnsFalseOfTheValidateAmount() {
		Boolean test = ValidatesFake.validateAmount(-1);
		assertFalse(test);
	}

	@Test
	public void testIfItReceivesPositiveValueOrZeroReturnsTrueOfTheValidatePrice() {
		Boolean test = ValidatesFake.validatePrice("1");
		assertTrue(test);
	}

	@Test
	public void testIfItReceivesNegativeValueReturnsFalseOfTheValidatePrice() {
		Boolean test = ValidatesFake.validatePrice("-1");
		assertFalse(test);
	}
}

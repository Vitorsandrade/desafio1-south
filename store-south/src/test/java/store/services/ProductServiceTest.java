package store.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import store.builders.ProductBuilder;
import store.entities.Product;

class ProductServiceTest {

	@Test
	public void testProductInstanceWithNullValue() {

		Product product = new ProductBuilder("nome", "categoria", BigDecimal.ZERO, 0).buildProductModel();

		assertEquals("nome", product.getName());
		assertEquals("categoria", product.getCategory());
		assertEquals(BigDecimal.ZERO, product.getPrice());
		assertEquals(0, product.getAmount());
		assertEquals(null, product.getColor());
		assertEquals(null, product.getDescription());
		assertEquals(null, product.getBarCode());
		assertEquals(null, product.getId());
		assertEquals(null, product.getDateValidity());
		assertEquals(null, product.getFabricationDate());
	}

	@Test
	public void testProductInstanceWithAllValues() {
		Product product = new ProductBuilder("nome", "categoria", BigDecimal.ZERO, 0).color("cor")
				.description("descrição").material("material").buildProduct();

		assertEquals("nome", product.getName());
		assertEquals("categoria", product.getCategory());
		assertEquals(BigDecimal.ZERO, product.getPrice());
		assertEquals(0, product.getAmount());
		assertEquals("cor", product.getColor());
		assertEquals("descrição", product.getDescription());
		assertEquals(null, product.getDateValidity());
		assertEquals(LocalDate.now(), product.getFabricationDate());
	}

	@Test
	public void testIfProductWasInsertedCorrectly() {
		Boolean test = ProductServiceFake.postProduct("nome", "categoria", BigDecimal.ONE, 0);

		if (test == true) {
			Product product = new ProductBuilder("nome", "categoria", BigDecimal.ONE, 0).buildProduct();
			assertEquals("nome", product.getName());
		}

	}

	@Test
	public void testIfProductWasNotInsertedCorrectly() {
		Boolean test = ProductServiceFake.postProduct("nome", "sd", BigDecimal.ONE, -9);
		assertEquals(test, false);
	}

	@Test
	public void testWhetherOrNotToCancelTheOperationByPassing1() {
		Boolean test = ProductServiceFake.cancelOrConfirm("1");
		assertEquals(test, true);
	}

	@Test
	public void testWhetherOrNotToCancelTheOperationByPassing2() {
		Boolean test = ProductServiceFake.cancelOrConfirm("2");
		assertEquals(test, false);
	}

	@Test
	public void testWhetherOrNotToCancelTheOperationByPassingInvalidEntry() {
		Boolean test = ProductServiceFake.cancelOrConfirm("ds");
		assertEquals(test, false);
	}

	@Test
	public void testIfYouCanFindProductInTheList() {
		Boolean test = ProductServiceFake.searchProduct("adt56ggd");
		assertEquals(test, true);
	}

	@Test
	public void testIfYouCanFindProductInTheListPassingIdInvalid() {
		Boolean test = ProductServiceFake.searchProduct("asdasdsa");
		assertEquals(test, false);
	}

}

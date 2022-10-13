package store.validations;

import java.math.BigDecimal;

public class ValidatesFake {

	public static Boolean validateAmount(Integer amount) {
		if (amount< 0) {
			return false;
		}
		return true;

	}
	
	public static Boolean validatePrice(String price){
		BigDecimal newPrice = new BigDecimal(price.replace(",", "."));
		
		if(newPrice.signum() < 0) {
			return false;
		}else {
			return true;
		}

	}
}

package com.lalmonacid.creditcard_service;

import com.lalmonacid.creditcard_service.controllers.CreditCardController;
import com.lalmonacid.creditcard_service.models.CreditCardFactory;
import com.lalmonacid.creditcard_service.models.Operation;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCard;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardNumber;
import com.lalmonacid.creditcard_service.services.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditCardWebServiceApplicationTests {

	@Autowired
	private CreditCardController creditCardController;

	@Autowired
	private CreditCardService creditCardService;

	@Test
	void contextLoads() {
	}

	// Endpoint: /api/creditcards/operationRate

	@Test
	public void test01_OperationRateIsCalculatedCorrectlyForVisaCards() {
		ResponseEntity<Object> response = creditCardController.getOperationRate("VISA", 100.0);

		int lastTwoDigitsOfYear = LocalDate.now().getYear() % 100;
		Double expectedResult = (double) lastTwoDigitsOfYear / LocalDate.now().getMonth().getValue();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResult, response.getBody());
	}

	@Test
	public void test02_OperationRateIsCalculatedCorrectlyForAmexCards() {
		ResponseEntity<Object> response = creditCardController.getOperationRate("AMEX", 100.0);

		Double expectedResult = LocalDate.now().getMonth().getValue() * 0.1;

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResult, response.getBody());
	}

	@Test
	public void test03_OperationRateIsCalculatedCorrectlyForNaraCards() {
		ResponseEntity<Object> response = creditCardController.getOperationRate("NARA", 100.0);

		Double expectedResult = LocalDate.now().getDayOfMonth() / 2.0;

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResult, (Double) response.getBody());
	}

	@Test
	public void test04_InvalidBrandCausesError() {
		ResponseEntity<Object> response = creditCardController.getOperationRate("INVALID", 100.0);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error: Invalid credit card brand", response.getBody());
	}

	@Test
	public void test05_InvalidAmountCausesError() {
		ResponseEntity<Object> response = creditCardController.getOperationRate("VISA", 1000.0);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error: Amount must be less than 1000", response.getBody());
	}

	// Endpoint: /api/creditcards/operationRateFromCard

	@Test
	public void test06_OperationRateIsCalculatedCorrectlyForVisaNumberAndValidAmount() {
		String visaCreditCardNumber = "41234567890123";
		ResponseEntity<Object> response = creditCardController.getOperationRateFromCardAndAmount(visaCreditCardNumber, 400.0);

		int lastTwoDigitsOfYear = LocalDate.now().getYear() % 100;
		Double expectedResult = (double) lastTwoDigitsOfYear / LocalDate.now().getMonth().getValue();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResult, response.getBody());
	}

	@Test
	public void test07_OperationRateIsCalculatedCorrectlyForNaranjaNumberAndValidAmount() {
		String naranjaCreditCardNumber = "51234567890123";
		ResponseEntity<Object> response = creditCardController.getOperationRateFromCardAndAmount(naranjaCreditCardNumber, 400.0);

		Double expectedResult = LocalDate.now().getDayOfMonth() / 2.0;

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResult, (Double) response.getBody());
	}

	@Test
	public void test08_OperationRateIsCalculatedCorrectlyForAmexNumberAndValidAmount() {
		String amexCreditCardNumber = "31234567890123";
		ResponseEntity<Object> response = creditCardController.getOperationRateFromCardAndAmount(amexCreditCardNumber, 400.0);

		Double expectedResult = LocalDate.now().getMonth().getValue() * 0.1;

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResult, response.getBody());
	}

	@Test
	public void test09_NumberWithInvalidPrefixCausesError() {
		String invalidCreditCardNumber = "61234567890123";
		ResponseEntity<Object> response = creditCardController.getOperationRateFromCardAndAmount(invalidCreditCardNumber, 400.0);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error: Invalid credit card number", response.getBody());
	}

	@Test
	public void test10_NumberWithInvalidLengthCausesError() {
		String invalidCreditCardNumber = "3123456";
		ResponseEntity<Object> response = creditCardController.getOperationRateFromCardAndAmount(invalidCreditCardNumber, 400.0);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error: Invalid credit card number", response.getBody());
	}

	@Test
	public void test11_NumberWithInvalidCharacterCausesError() {
		String invalidCreditCardNumber = "51234A67890123";
		ResponseEntity<Object> response = creditCardController.getOperationRateFromCardAndAmount(invalidCreditCardNumber, 400.0);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error: Invalid credit card number", response.getBody());
	}

	@Test
	public void test11_InvalidAmountCausesError() {
		String invalidCreditCardNumber = "51234567890123";
		ResponseEntity<Object> response = creditCardController.getOperationRateFromCardAndAmount(invalidCreditCardNumber, 1000.0);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Error: Amount must be less than 1000", response.getBody());
	}

	// Tests Ejercicio 1

	@Test
	public void test12_canGetCreditCardInformation() {
		String naranjaCreditCardNumber = "51234567890123";
		try {
			CreditCard card = CreditCardFactory.ofNumber(CreditCardNumber.from(naranjaCreditCardNumber));
			assertEquals(
					"Cardholder: John Doe\n" +
					"Brand: Naranja\n" +
					"Number: 51234567890123\n" +
					"Expiration Date: 2025-12", card.getCardInformation());
		} catch (Exception e) {
            // no exception
        }
    }

	@Test
	public void test13_OperationWithValidCardAndAmountIsValid() {
		String amexCreditCardNumber = "51234567890123";

		try {
			CreditCard card = CreditCardFactory.ofNumber(CreditCardNumber.from(amexCreditCardNumber));
			Operation operation = Operation.of(card, 100.0);
			assertTrue(Operation.isValidAmount(100.0));
		} catch (Exception e) {
			// no exception
		}
	}

	@Test
	public void test14_OperationWithExpiredCardAndValidAmountIsInvalid() {
		String amexCreditCardNumber = "51234567890123";

		try {
			CreditCard card = CreditCardFactory.expiredCard();
			Operation operation = Operation.of(card, 100.0);
		} catch (Exception e) {
			assertEquals("Cannot operate with an expired card", e.getMessage());
		}
	}

	@Test
	public void test15_OperationWithValidCardAndInvalidAmountIsInvalid() {
		String amexCreditCardNumber = "51234567890123";

		try {
			CreditCard card = CreditCardFactory.ofNumber(CreditCardNumber.from(amexCreditCardNumber));
			Operation operation = Operation.of(card, 1000.0);
		} catch (Exception e) {
			assertEquals("Amount must be less than 1000", e.getMessage());
		}
	}

	@Test
	public void test16_IdenticalCardsAreEqual() {
		String naranjaCreditCardNumber = "51234567890123";

		try {
			CreditCard card1 = CreditCardFactory.ofNumber(CreditCardNumber.from(naranjaCreditCardNumber));
			CreditCard card2 = CreditCardFactory.ofNumber(CreditCardNumber.from(naranjaCreditCardNumber));
			assertTrue(card1.equals(card2));
		} catch (Exception e) {
			// no exception
		}
	}

	@Test
	public void test17_DifferentCardsAreNotEqual() {
 		String amexCreditCardNumber = "51234567890123";
		String visaCreditCardNumber = "41234567890123";

		try {
			CreditCard card1 = CreditCardFactory.ofNumber(CreditCardNumber.from(amexCreditCardNumber));
			CreditCard card2 = CreditCardFactory.ofNumber(CreditCardNumber.from(visaCreditCardNumber));
			assertFalse(card1.equals(card2));
		} catch (Exception e) {
			// no exception
		}
	}
}

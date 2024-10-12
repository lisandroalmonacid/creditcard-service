package com.lalmonacid.creditcard_service;

import com.lalmonacid.creditcard_service.models.Operation;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCard;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardBrand;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardHolder;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardNumber;

import java.time.YearMonth;

public class Ejercicio1 {
    public static void main( String[] args ) throws Exception {
        CreditCardBrand brand = CreditCardBrand.from("NARA");
        CreditCardNumber number = CreditCardNumber.from("3123456789123");
        CreditCardHolder cardholder = CreditCardHolder.from("John", "Doe");
        YearMonth expirationDate = YearMonth.of(2025, 12);
        CreditCard creditCard = new CreditCard(brand, number, cardholder, expirationDate);
        System.out.println(creditCard.getCardInformation());

        Double amount = 999.99;
        Operation validOperation = Operation.of(creditCard, amount);
        boolean isValidOperation = validOperation.isValid();
        System.out.println("Is operation valid? " + isValidOperation);

        Boolean isCardExpired = creditCard.isExpired();
        System.out.println("Is card expired? " + isCardExpired);

        CreditCard creditCard2 = new CreditCard(brand, number, cardholder, expirationDate);
        Boolean areCardsEqual = creditCard.equals(creditCard2);
        System.out.println("Are cards equal? " + areCardsEqual);

        double rate = Operation.getRateFromBrandAndAmount(brand, amount);
        System.out.println("Rate: " + rate);
    }
}

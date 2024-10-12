package com.lalmonacid.creditcard_service.models;

import com.lalmonacid.creditcard_service.models.creditcard.CreditCard;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardBrand;

public class Operation {
    private final CreditCard creditCard;
    private final Double amount;

    private Operation(CreditCard creditCard, Double amount) {
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public static Operation of(CreditCard creditCard, Double amount) throws Exception {
        assertValidCard(creditCard);
        assertValidAmount(amount);
        return new Operation(creditCard, amount);
    }

    public static Double getRateFromBrandAndAmount(CreditCardBrand brand, Double amount) throws Exception {
        assertValidAmount(amount);
        return brand.getRateFromAmount(amount);
    }

    public Double getRate() {
        return creditCard.getBrandRate(amount);
    }

    private static void assertValidCard(CreditCard creditCard) throws Exception {
        if (!isValidCreditCard(creditCard)) {
            throw new Exception("Cannot operate with an expired card");
        }
    }

    private static void assertValidAmount(Double amount) throws Exception {
        if (!isValidAmount(amount)) {
            throw new Exception("Amount must be more than zero and less than 1000");
        }
    }

    public static Boolean isValidCreditCard(CreditCard creditCard) {
        return !creditCard.isExpired();
    }

    public static Boolean isValidAmount(Double amount) {
        return amount > 0 && amount < 1000;
    }

    public boolean isValid() {
        return isValidAmount(amount) && isValidCreditCard(creditCard);
    }
}

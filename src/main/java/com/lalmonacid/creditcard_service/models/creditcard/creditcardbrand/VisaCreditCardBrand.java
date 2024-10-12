package com.lalmonacid.creditcard_service.models.creditcard.creditcardbrand;

import com.lalmonacid.creditcard_service.models.creditcard.CreditCardBrand;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardNumber;

import java.time.LocalDate;

public class VisaCreditCardBrand extends CreditCardBrand {
    @Override
    public Double getRateFromAmount(Double amount) {
        int lastTwoDigitsOfYear = LocalDate.now().getYear() % 100;
        return (double) lastTwoDigitsOfYear / LocalDate.now().getMonth().getValue();
    }

    public String name() {
        return "Visa";
    }

    public static boolean isNumberForBrand(CreditCardNumber number) {
        return number.value().startsWith("4");
    }
}
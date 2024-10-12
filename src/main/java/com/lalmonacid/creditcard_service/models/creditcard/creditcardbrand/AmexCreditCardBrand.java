package com.lalmonacid.creditcard_service.models.creditcard.creditcardbrand;

import com.lalmonacid.creditcard_service.models.creditcard.CreditCardBrand;

import java.time.LocalDate;

public class AmexCreditCardBrand extends CreditCardBrand {
    @Override
    public Double getRateFromAmount(Double amount) {
        return LocalDate.now().getMonth().getValue() * 0.1;
    }

    public String name() {
        return "American Express";
    }
}
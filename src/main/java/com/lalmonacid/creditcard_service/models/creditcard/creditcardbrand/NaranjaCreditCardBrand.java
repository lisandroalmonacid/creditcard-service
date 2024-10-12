package com.lalmonacid.creditcard_service.models.creditcard.creditcardbrand;

import com.lalmonacid.creditcard_service.models.creditcard.CreditCardBrand;

import java.time.LocalDate;

public class NaranjaCreditCardBrand extends CreditCardBrand {
    @Override
    public Double getRateFromAmount(Double amount) {
        return LocalDate.now().getDayOfMonth() / 2.0;
    }

    public String name() {
        return "Naranja";
    }
}

package com.lalmonacid.creditcard_service.models.creditcard;

import com.lalmonacid.creditcard_service.models.creditcard.creditcardbrand.AmexCreditCardBrand;
import com.lalmonacid.creditcard_service.models.creditcard.creditcardbrand.NaranjaCreditCardBrand;
import com.lalmonacid.creditcard_service.models.creditcard.creditcardbrand.VisaCreditCardBrand;

public abstract class CreditCardBrand {
    public static final String NARA = "NARA";
    public static final String VISA = "VISA";
    public static final String AMEX = "AMEX";

    public static CreditCardBrand from(String brand) throws Exception {
        return switch (brand) {
            case NARA -> new NaranjaCreditCardBrand();
            case VISA -> new VisaCreditCardBrand();
            case AMEX -> new AmexCreditCardBrand();
            default -> throw new Exception("Invalid credit card brand");
        };
    }

    public static CreditCardBrand fromNumber(CreditCardNumber creditCardNumber) throws Exception {
        if (AmexCreditCardBrand.isNumberForBrand(creditCardNumber)) {
            return CreditCardBrand.from(AMEX);
        } else if (NaranjaCreditCardBrand.isNumberForBrand(creditCardNumber)) {
            return CreditCardBrand.from(NARA);
        } else if (VisaCreditCardBrand.isNumberForBrand(creditCardNumber)) {
            return CreditCardBrand.from(VISA);
        } else {
            throw new Exception("Invalid credit card number");
        }
    }

    public abstract Double getRateFromAmount(Double amount);
    public abstract String name();

    public boolean equals(CreditCardBrand brand) {
        return this.name().equals(brand.name());
    }

    //public abstract static boolean isNumberForBrand(CreditCardNumber number);
}

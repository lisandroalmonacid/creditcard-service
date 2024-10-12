package com.lalmonacid.creditcard_service.models;

import com.lalmonacid.creditcard_service.models.creditcard.CreditCardHolder;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCard;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardBrand;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardNumber;

import java.time.YearMonth;

public class CreditCardFactory {
    private static final YearMonth DECEMBER_2023 = YearMonth.of(2023, 12);
    private static final YearMonth DECEMBER_2025 = YearMonth.of(2025, 12);
    private static final CreditCardHolder JOHN_DOE = CreditCardHolder.from("John", "Doe");
    private static final CreditCardNumber EXAMPLE_CREDIT_CARD_NUMBER = CreditCardNumber.from("378282246310005");

    public static CreditCard expiredCard() throws Exception {
        return new CreditCard(
                CreditCardBrand.from("AMEX"),
                EXAMPLE_CREDIT_CARD_NUMBER,
                JOHN_DOE,
                DECEMBER_2023
        );
    }

    public static CreditCard ofNumber(CreditCardNumber number) throws Exception {
        return withBrandAndNumber(number.getBrand(), number);
    }

    public static CreditCard withBrandAndNumber(CreditCardBrand brand, CreditCardNumber number) throws Exception {
        return new CreditCard(
                brand,
                number,
                JOHN_DOE,
                DECEMBER_2025
        );
    }
}

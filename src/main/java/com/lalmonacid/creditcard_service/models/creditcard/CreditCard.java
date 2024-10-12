package com.lalmonacid.creditcard_service.models.creditcard;

import java.time.YearMonth;

public class CreditCard {
    private final CreditCardBrand brand;
    private final CreditCardNumber number;
    private final CreditCardHolder cardholder;
    private final YearMonth expirationDate;

    public CreditCard (
        CreditCardBrand brand,
        CreditCardNumber number,
        CreditCardHolder cardholder,
        YearMonth expirationDate
    ) {
        this.brand = brand;
        this.number = number;
        this.cardholder = cardholder;
        this.expirationDate = expirationDate;
    }

    public Double getBrandRate(Double amount) {
        return brand.getRateFromAmount(amount);
    }

    public String getCardInformation() {
        return String.format(
                "Cardholder: %s\nBrand: %s\nNumber: %s\nExpiration Date: %s",
                cardholder.toString(),
                brand.name(),
                number.value(),
                expirationDate.toString()
        );
    }

    public Boolean isExpired() {
        return expirationDate.isBefore(YearMonth.now());
    }

    public Boolean equals(CreditCard creditCard) {
        return
            this.brand.equals(creditCard.brand) &&
            this.number.equals(creditCard.number) &&
            this.cardholder.equals(creditCard.cardholder) &&
            this.expirationDate.equals(creditCard.expirationDate);
    }
}

package com.lalmonacid.creditcard_service.models.creditcard;

public class CreditCardNumber {
    private final String number;

    private CreditCardNumber(String number) {
        this.number = number;
    }

    public static CreditCardNumber from(String number) {
        if (!isValidNumber(number)) {
            throw new IllegalArgumentException("Invalid credit card number");
        }
        return new CreditCardNumber(number);
    }

    public String value() {
        return number;
    }

    public Boolean isAmex() {
        return number.startsWith("3");
    }

    public Boolean isVisa() {
        return number.startsWith("4");
    }

    public Boolean isNara() {
        return number.startsWith("5");
    }

    public CreditCardBrand getBrand() throws Exception {
        return CreditCardBrand.fromNumber(this);
    }

    private static boolean isValidNumber(String number) {
        return number != null && number.matches("\\d{13,19}");
    }

    public boolean equals(CreditCardNumber creditCardNumber) {
        return this.number.equals(creditCardNumber.number);
    }
}

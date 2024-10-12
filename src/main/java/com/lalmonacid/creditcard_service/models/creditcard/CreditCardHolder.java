package com.lalmonacid.creditcard_service.models.creditcard;

public class CreditCardHolder {
    private final String firstName;
    private final String lastName;

    private CreditCardHolder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static CreditCardHolder from(String firstName, String lastName) {
        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("Invalid cardholder name");
        }
        return new CreditCardHolder(firstName, lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public Boolean equals(CreditCardHolder cardholder) {
        return this.firstName.equals(cardholder.firstName) && this.lastName.equals(cardholder.lastName);
    }
}


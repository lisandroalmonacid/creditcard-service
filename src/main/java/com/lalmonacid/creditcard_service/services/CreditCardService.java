package com.lalmonacid.creditcard_service.services;

import com.lalmonacid.creditcard_service.models.creditcard.CreditCard;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardBrand;
import com.lalmonacid.creditcard_service.models.Operation;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardNumber;
import com.lalmonacid.creditcard_service.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public Double getOperationRateWithBrandAndAmount(String brandName, Double amount) throws Exception {
        CreditCardBrand brand = CreditCardBrand.from(brandName);
        return Operation.getRateFromBrandAndAmount(brand, amount);
    }

    public Double getOperationRateWithCardNumberAndAmount(String creditCardNumber, Double amount) throws Exception {
        CreditCardNumber number = CreditCardNumber.from(creditCardNumber);
        CreditCard card = creditCardRepository.getByNumber(number);
        return Operation.of(card, amount).getRate();
    }
}

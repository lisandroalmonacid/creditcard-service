package com.lalmonacid.creditcard_service.repositories;

import com.lalmonacid.creditcard_service.models.CreditCardFactory;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCard;
import com.lalmonacid.creditcard_service.models.creditcard.CreditCardNumber;
import org.springframework.stereotype.Repository;

@Repository
public class CreditCardRepository {
    public CreditCard getByNumber(CreditCardNumber number) throws Exception {
        return CreditCardFactory.ofNumber(number);
    }
}

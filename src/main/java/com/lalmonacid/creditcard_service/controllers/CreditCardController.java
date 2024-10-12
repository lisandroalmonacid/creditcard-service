package com.lalmonacid.creditcard_service.controllers;

import com.lalmonacid.creditcard_service.services.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/operationRate")
    public ResponseEntity<Object> getOperationRate(@RequestParam String brandName, @RequestParam Double amount) {
        try {
            Double rate = creditCardService.getOperationRateWithBrandAndAmount(brandName, amount);
            return ResponseEntity.ok(rate);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/operationRateFromCard")
    public ResponseEntity<Object> getOperationRateFromCardAndAmount(@RequestParam String creditCardNumber, @RequestParam Double amount) {
        try {
            Double rate = creditCardService.getOperationRateWithCardNumberAndAmount(creditCardNumber, amount);
            return ResponseEntity.ok(rate);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

}

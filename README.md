# Credit Card Web Service

This is a simple credit card web service developed in Spring Boot.
[Link to Swagger UI](http://18.191.149.160/swagger-ui/index.html)

It calculates an Operation Rate depending on the specified Credit Card Brand:

- **AMEX:** Current month / 10.
- **NARA:** Current day of the month / 2.
- **VISA:** Last two digits of the current year / current month.

## API endpoints:

### `/api/creditcards/operationRate`
- Brand: valid brands are "VISA", "NARA" and "AMEX".
- Amount: must be a number between 0 and 1000.

### `/api/creditcards/operationRateFromCard`
- Credit Card Number: must be a number between 13 and 19 characters long. It should start with 3, 4, or 5.
  - Starting with 3: it's an **AMEX** number.
  - Starting with 4: it's a **VISA** number.
  - Starting with 5: it's a **NARA** number.
- Amount: must be a number (bigger than 0 and less than 1000).

An extra executable file is included (`QuickShowcase.java`) to demonstrate other developed features.

<h1 align="center"> Purchase - API </h1>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## ✅ Features

A simple purchase api that has 2 endpoints:

- `/purchase-api/purchase/savePurchase` : To save your purchase. You can access PurchaseController::purchaseService.savePurchase to 
see what's being validated. The API will return a 201 status if everything's ok or 400
status if there's something wrong or missing in your request.


- `/purchase-api/product/getAvailableProducts` : To get all available products.

This API is also part of a greater implementation of other two APIs that simulate a purchase, validate and notify the user about the process.

## ⚒️ Technologies

- Java 11
- Spring Boot
- Maven
- Swagger
- JUnit
- RabbitMQ
- Docker
- JPA / Hibernate
- H2

## ☁️ Host

- AWS - Elastic Beanstalk

## 🔗 Links

- 📖 Swagger Documentation: http://purchase-api-env.eba-maubepbp.us-east-1.elasticbeanstalk.com/purchase-api/swagger-ui.html
- 😄 My LinkedIn Profile: https://www.linkedin.com/in/gabriel-santos-20737b171
- 🔗 Related APIs:
    * https://github.com/gabrielrabelomachadosantos/creditcardvalidation-api
    * https://github.com/gabrielrabelomachadosantos/notifications-api

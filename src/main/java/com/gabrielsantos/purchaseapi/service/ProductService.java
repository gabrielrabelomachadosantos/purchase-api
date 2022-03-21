package com.gabrielsantos.purchaseapi.service;

import com.gabrielsantos.purchaseapi.enums.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;

@Service
public class ProductService {

    public ResponseEntity<String> getAvailableProducts() {
        ArrayList<Product> allAvailableProducts = new ArrayList<>(EnumSet.allOf(Product.class));

        String productsDescription = "";

        for (Product product : allAvailableProducts) {
            productsDescription += "\n" + product.getDescription() + " / ID: " + product.getId();
        }

        StringBuilder supportedCardIssuers = new StringBuilder(productsDescription);
        supportedCardIssuers.deleteCharAt(0);

        return new ResponseEntity<>(String.valueOf(supportedCardIssuers), HttpStatus.OK);
    }

}

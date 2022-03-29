package com.gabrielsantos.purchaseapi.builder;

import com.gabrielsantos.purchaseapi.dto.AddressDTO;
import com.gabrielsantos.purchaseapi.dto.CreditCardDTO;
import com.gabrielsantos.purchaseapi.dto.PurchaseDTO;
import com.gabrielsantos.purchaseapi.enums.Product;
import com.gabrielsantos.purchaseapi.enums.PurchaseStatus;
import com.gabrielsantos.purchaseapi.model.Address;
import com.gabrielsantos.purchaseapi.model.Purchase;

import java.math.BigDecimal;
import java.util.Date;

public class BuilderMethods {

    public PurchaseDTO buildPurchaseDTO() {
        return PurchaseDTO.builder()
                .buyerEmail("randomemail@gmail.com")
                .buyerName("Gabriel Santos")
                .buyerSSN("123456789")
                .buyerZipCode("90209")
                .purchaseStatus(PurchaseStatus.PENDING)
                .price(BigDecimal.valueOf(4500.00))
                .product(Product.PLAY_STATION_5)
                .creditCardDTO(buildCreditCardDTO())
                .purchaseDate(System.currentTimeMillis())
                .build();
    }

    public AddressDTO buildAddressDTO() {
        return AddressDTO.builder()
                .zipCode("90209")
                .latitude("34.070018")
                .longitude("-118.390264")
                .city("Beverly Hills")
                .state("CA")
                .build();
    }

    public Address buildAddress() {
        return Address.builder()
                .id(1L)
                .purchase(new Purchase())
                .zipCode("90209")
                .latitude("34.070018")
                .longitude("-118.390264")
                .city("Beverly Hills")
                .state("CA")
                .build();
    }

    public CreditCardDTO buildCreditCardDTO() {
        return CreditCardDTO.builder()
                .expiringDate(new Date())
                .number("5545 0442 2990 0872")
                .build();
    }

}

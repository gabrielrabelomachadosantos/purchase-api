package com.gabrielsantos.purchaseapi.builder;

import com.gabrielsantos.purchaseapi.dto.AddressDTO;
import com.gabrielsantos.purchaseapi.dto.CreditCardDTO;
import com.gabrielsantos.purchaseapi.dto.PurchaseDTO;
import com.gabrielsantos.purchaseapi.enums.Product;
import com.gabrielsantos.purchaseapi.enums.PurchaseStatus;
import com.gabrielsantos.purchaseapi.model.Address;
import com.gabrielsantos.purchaseapi.model.Purchase;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

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
                .expiringDateInMillis(System.currentTimeMillis())
                .number(5545044229900872L)
                .build();
    }

    public HttpRequest.Builder buildZipCodeHttpRequest() {
        return (HttpRequest.Builder) HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.zipcodeapi.com/rest/DemoOnly00aog1kYaaFchsCZnpvS8N9AwPnA8Axpgti7Pebeh4TFUqB2Eqf9DFaQ/info.json/90209/degrees"))
                .timeout(Duration.ofSeconds(5))
                .build();
    }

}

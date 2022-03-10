package com.gabrielsantos.purchaseapi.dto;

import com.gabrielsantos.purchaseapi.enums.Product;
import com.gabrielsantos.purchaseapi.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long id;

    private Product product;

    private BigDecimal price;

    private String buyerName;

    private String buyerSSN;

    private String buyerEmail;

    private String buyerZipCode;

    private Long purchaseDate;

    private CreditCardDTO creditCardDTO;

    private PurchaseStatus purchaseStatus;

}

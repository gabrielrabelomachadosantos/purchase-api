package com.gabrielsantos.purchaseapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielsantos.purchaseapi.enums.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Product product;

    private BigDecimal price;

    private String buyerName;

    private String buyerSSN;

    private String buyerEmail;

    private String buyerZipCode;

    private Long purchaseDate;

}

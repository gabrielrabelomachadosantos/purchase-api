package com.gabrielsantos.purchaseapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielsantos.purchaseapi.enums.Product;
import com.gabrielsantos.purchaseapi.enums.PurchaseStatus;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(hidden = true)
    private Long id;

    private Product product;

    @ApiModelProperty(example = "4500.00")
    private BigDecimal price;

    @ApiModelProperty(example = "Giovanni Giorgio")
    private String buyerName;

    @ApiModelProperty(example = "123456789")
    private String buyerSSN;

    @ApiModelProperty(example = "randomemail@email.com")
    private String buyerEmail;

    @ApiModelProperty(example = "90209")
    private String buyerZipCode;

    @JsonIgnore
    private Long purchaseDate;

    private CreditCardDTO creditCardDTO;

    @ApiModelProperty(hidden = true)
    private PurchaseStatus purchaseStatus;

}

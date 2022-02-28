package com.gabrielsantos.purchaseapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

    private String number;

    private String ownerName;

    private Long expiringDate;

    private Integer securityCode;

}

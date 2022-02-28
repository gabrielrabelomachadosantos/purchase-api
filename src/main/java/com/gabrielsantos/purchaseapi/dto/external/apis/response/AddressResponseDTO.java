package com.gabrielsantos.purchaseapi.dto.external.apis.response;

import com.gabrielsantos.purchaseapi.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

    private Long id;

    private Purchase purchase;

    private String zip_code;

    private String lat;

    private String lng;

    private String city;

    private String state;

}

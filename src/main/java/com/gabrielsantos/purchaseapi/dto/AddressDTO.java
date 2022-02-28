package com.gabrielsantos.purchaseapi.dto;

import com.gabrielsantos.purchaseapi.dto.external.apis.response.AddressResponseDTO;
import com.gabrielsantos.purchaseapi.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    private Purchase purchase;

    private String zipCode;

    private String latitude;

    private String longitude;

    private String city;

    private String state;

    public AddressDTO(AddressResponseDTO addressResponseDTO) {
        this.zipCode = addressResponseDTO.getZip_code();
        this.latitude = addressResponseDTO.getLat();
        this.longitude = addressResponseDTO.getLng();
        this.city = addressResponseDTO.getCity();
        this.state = addressResponseDTO.getState();
    }

}

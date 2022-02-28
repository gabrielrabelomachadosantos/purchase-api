package com.gabrielsantos.purchaseapi.model;

import com.gabrielsantos.purchaseapi.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address_table")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_fk")
    private Purchase purchase;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    public Address(AddressDTO addressDTO) {
        this.zipCode = addressDTO.getZipCode();
        this.latitude = addressDTO.getLatitude();
        this.longitude = addressDTO.getLongitude();
        this.city = addressDTO.getCity();
        this.state = addressDTO.getState();
    }

}

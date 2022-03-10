package com.gabrielsantos.purchaseapi.model;

import com.gabrielsantos.purchaseapi.dto.PurchaseDTO;
import com.gabrielsantos.purchaseapi.enums.Product;
import com.gabrielsantos.purchaseapi.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase_table")
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private Product product;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "buyer_name", nullable = false)
    private String buyerName;

    @Column(name = "buyer_ssn", nullable = false)
    private String buyerSSN;

    @Column(name = "buyer_email", nullable = false)
    private String buyerEmail;

    @Column(name = "buyer_zip_code", nullable = false)
    private String buyerZipCode;

    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "purchase_status", nullable = false)
    private PurchaseStatus purchaseStatus;

    public Purchase(PurchaseDTO purchaseDTO) {
        this.product = purchaseDTO.getProduct();
        this.price = purchaseDTO.getPrice();
        this.buyerName = purchaseDTO.getBuyerName();
        this.buyerSSN = purchaseDTO.getBuyerSSN();
        this.buyerEmail = purchaseDTO.getBuyerEmail();
        this.buyerZipCode = purchaseDTO.getBuyerZipCode();
        this.purchaseDate = new Date(purchaseDTO.getPurchaseDate());
        this.purchaseStatus = PurchaseStatus.PENDING;
    }

}

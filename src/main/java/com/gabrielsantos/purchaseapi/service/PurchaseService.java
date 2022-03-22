package com.gabrielsantos.purchaseapi.service;

import com.gabrielsantos.purchaseapi.dto.CreditCardDTO;
import com.gabrielsantos.purchaseapi.dto.PurchaseDTO;
import com.gabrielsantos.purchaseapi.enums.PurchaseStatus;
import com.gabrielsantos.purchaseapi.exception.BadRequestException;
import com.gabrielsantos.purchaseapi.model.Address;
import com.gabrielsantos.purchaseapi.model.Purchase;
import com.gabrielsantos.purchaseapi.repository.AddressRepository;
import com.gabrielsantos.purchaseapi.repository.PurchaseRepository;
import com.gabrielsantos.purchaseapi.service.rabbitmq.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final AddressRepository addressRepository;

    private final AddressService addressService;

    private final Producer producer;

    public ResponseEntity<PurchaseDTO> savePurchase(PurchaseDTO purchaseDTO) {
        validatePurchaseDTO(purchaseDTO);

        Address address = buildAddress(purchaseDTO.getBuyerZipCode());

        Purchase purchase = new Purchase(purchaseDTO);
        purchaseRepository.save(purchase);
        purchaseDTO.setId(purchase.getId());
        purchaseDTO.setPurchaseStatus(PurchaseStatus.PENDING);

        address.setPurchase(purchase);
        addressRepository.save(address);

        producer.sendToRegisteredPurchaseQueue(purchaseDTO);

        return new ResponseEntity<>(purchaseDTO, HttpStatus.CREATED);
    }

    public void updatePurchaseStatus(PurchaseDTO purchaseDTO) {
        purchaseRepository.setPurchaseStatus(purchaseDTO.getId(), purchaseDTO.getPurchaseStatus());

        if (PurchaseStatus.APPROVED.equals(purchaseDTO.getPurchaseStatus())) {
            producer.sendToApprovedPurchaseQueue(purchaseDTO);
        } else {
            producer.sendToReprovedPurchaseQueue(purchaseDTO);
        }
    }

    public Address buildAddress(String zipCode) {
        return new Address(addressService.getFullAddress(zipCode));
    }

    private void validatePurchaseDTO(PurchaseDTO purchaseDTO) {
        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getProduct(), "The product must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getBuyerEmail(), "The email must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getPrice(), "The price must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getBuyerName(), "The buyer name must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getBuyerSSN(), "The buyer ssn must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getBuyerZipCode(), "The buyer zip code must not be null.");

        if (purchaseDTO.getPurchaseDate() == null || purchaseDTO.getPurchaseDate() == 0) {
            purchaseDTO.setPurchaseDate(System.currentTimeMillis());
        }

        purchaseDTO.setPurchaseStatus(PurchaseStatus.PENDING);

        validateCreditCardDTO(purchaseDTO.getCreditCardDTO());
    }

    private void validateCreditCardDTO(CreditCardDTO creditCardDTO) {
        validateIfObjectIsNullAndThrowExceptionIfTrue(
                creditCardDTO.getNumber(), "The credit card number must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                creditCardDTO.getExpiringDate(), "The credit card expiring date must not be null.");
    }

    private void validateIfObjectIsNullAndThrowExceptionIfTrue(Object object, String exceptionMessage) {
        if (object instanceof String && !StringUtils.hasText(String.valueOf(object))) {
            throw new BadRequestException(exceptionMessage);
        } else if (object instanceof Number && ((Number) object).intValue() == 0) {
            throw new BadRequestException(exceptionMessage);
        } else if (object == null) {
            throw new BadRequestException(exceptionMessage);
        }
    }

}

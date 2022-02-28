package com.gabrielsantos.purchaseapi.service;

import com.gabrielsantos.purchaseapi.dto.PurchaseDTO;
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

        Address address = new Address(addressService.getFullAddress(purchaseDTO.getBuyerZipCode()));

        Purchase purchase = new Purchase(purchaseDTO);
        purchaseRepository.save(purchase);
        purchaseDTO.setId(purchase.getId());

        address.setPurchase(purchase);
        addressRepository.save(address);

        producer.sendToQueue(purchase);

        return new ResponseEntity<>(purchaseDTO, HttpStatus.CREATED);
    }

    private void validatePurchaseDTO(PurchaseDTO purchaseDTO) {
        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getProduct(), "The productId must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getPrice(), "The price must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getBuyerName(), "The buyerName must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getBuyerSSN(), "The buyerSsn must not be null.");

        validateIfObjectIsNullAndThrowExceptionIfTrue(
                purchaseDTO.getBuyerZipCode(), "The buyerZipCode must not be null.");
    }

    private void validateIfObjectIsNullAndThrowExceptionIfTrue(Object object, String exceptionMessage) {
        if (object instanceof String && !StringUtils.hasText(String.valueOf(object))) {
            throw new BadRequestException(exceptionMessage);
        } else {
            if (object == null) {
                throw new BadRequestException(exceptionMessage);
            }
        }
    }

}

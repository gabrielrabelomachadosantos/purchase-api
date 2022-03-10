package com.gabrielsantos.purchaseapi.service;

import com.gabrielsantos.purchaseapi.builder.BuilderMethods;
import com.gabrielsantos.purchaseapi.enums.PurchaseStatus;
import com.gabrielsantos.purchaseapi.exception.BadRequestException;
import com.gabrielsantos.purchaseapi.repository.AddressRepository;
import com.gabrielsantos.purchaseapi.repository.PurchaseRepository;
import com.gabrielsantos.purchaseapi.service.rabbitmq.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private AddressService addressService;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private Producer producer;

    private final BuilderMethods builderMethods = new BuilderMethods();

    @BeforeEach
    void init() {
        Mockito.lenient().when(addressService.getFullAddress(builderMethods.buildPurchaseDTO().getBuyerZipCode()))
                .thenReturn(builderMethods.buildAddressDTO());

        Mockito.lenient().doReturn(builderMethods.buildAddress())
                .when(Mockito.mock(PurchaseService.class)).buildAddress(builderMethods.buildPurchaseDTO().getBuyerZipCode());
    }

    @Test
    @DisplayName("Saving purchase successfully")
    void purchaseSaveSuccessTest() {
        var response = purchaseService.savePurchase(builderMethods.buildPurchaseDTO());

        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Validate purchaseDTO with null product")
    void validatePurchaseDTONullProductTest() {
        var purchaseDTO = builderMethods.buildPurchaseDTO();
        purchaseDTO.setProduct(null);

        Throwable exception = assertThrows(BadRequestException.class,() -> purchaseService.savePurchase(purchaseDTO));
        assertEquals("The product must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Validate purchaseDTO with null email")
    void validatePurchaseDTONullEmailTest() {
        var purchaseDTO = builderMethods.buildPurchaseDTO();
        purchaseDTO.setBuyerEmail(null);

        Throwable exception = assertThrows(BadRequestException.class,() -> purchaseService.savePurchase(purchaseDTO));
        assertEquals("The email must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Validate purchaseDTO with null price")
    void validatePurchaseDTONullPriceTest() {
        var purchaseDTO = builderMethods.buildPurchaseDTO();
        purchaseDTO.setPrice(null);

        Throwable exception = assertThrows(BadRequestException.class,() -> purchaseService.savePurchase(purchaseDTO));
        assertEquals("The price must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Validate purchaseDTO with null buyerName")
    void validatePurchaseDTONullBuyerNameTest() {
        var purchaseDTO = builderMethods.buildPurchaseDTO();
        purchaseDTO.setBuyerName(null);

        Throwable exception = assertThrows(BadRequestException.class,() -> purchaseService.savePurchase(purchaseDTO));
        assertEquals("The buyer name must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Validate purchaseDTO with null buyerSSN")
    void validatePurchaseDTONullSSNTest() {
        var purchaseDTO = builderMethods.buildPurchaseDTO();
        purchaseDTO.setBuyerSSN(null);

        Throwable exception = assertThrows(BadRequestException.class,() -> purchaseService.savePurchase(purchaseDTO));
        assertEquals("The buyer ssn must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Validate purchaseDTO with null buyerZip")
    void validatePurchaseDTONullBuyerZipCodeTest() {
        var purchaseDTO = builderMethods.buildPurchaseDTO();
        purchaseDTO.setBuyerZipCode(null);

        Throwable exception = assertThrows(BadRequestException.class,() -> purchaseService.savePurchase(purchaseDTO));
        assertEquals("The buyer zip code must not be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Updating purchaseStatus successfully")
    void updatingPurchaseStatusSuccessfully() {
        var purchaseDTO = builderMethods.buildPurchaseDTO();

        assertDoesNotThrow(() -> purchaseService.updatePurchaseStatus(purchaseDTO));

        purchaseDTO.setPurchaseStatus(PurchaseStatus.APPROVED);

        assertDoesNotThrow(() -> purchaseService.updatePurchaseStatus(purchaseDTO));
    }

}

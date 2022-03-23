package com.gabrielsantos.purchaseapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsantos.purchaseapi.PurchaseApiApplication;
import com.gabrielsantos.purchaseapi.builder.BuilderMethods;
import com.gabrielsantos.purchaseapi.service.PurchaseService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("prod")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PurchaseApiApplication.class)
public class PurchaseControllerService {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ObjectMapper objectMapper;

    private final BuilderMethods builderMethods = new BuilderMethods();

    private static final String PURCHASE_PATH = "/purchase";

    @Test
    @SneakyThrows
    @DisplayName("Save purchase successfully integration test")
    void savePurchaseSuccessfully() {
        var requestBody = builderMethods.buildPurchaseDTO();

        mockMvc.perform(post(PURCHASE_PATH + "/savePurchase")
                .content(objectMapper.writeValueAsString(requestBody))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}

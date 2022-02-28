package com.gabrielsantos.purchaseapi.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsantos.purchaseapi.dto.AddressDTO;
import com.gabrielsantos.purchaseapi.dto.response.AddressResponseDTO;
import com.gabrielsantos.purchaseapi.exception.BadRequestException;
import com.gabrielsantos.purchaseapi.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {

    private final ObjectMapper objectMapper;

    @Value(value = "${external-apis.zipcodeapi.url}")
    private String zipCodeApiUrl;

    @Value(value = "${external-apis.zipcodeapi.application-key}")
    private String zipCodeApiApplicationKey;

    private static final Integer CONNECTION_TIMEOUT_IN_SECONDS = 5;

    @SneakyThrows
    public AddressDTO getFullAddress(String zipCode) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(zipCodeApiUrl + zipCodeApiApplicationKey + "/info.json/" + zipCode + "/degrees"))
                .timeout(Duration.ofSeconds(CONNECTION_TIMEOUT_IN_SECONDS))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT_IN_SECONDS))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        validateResponse(response);

        AddressResponseDTO addressResponseDTO = objectMapper
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .readValue(response.body(), AddressResponseDTO.class);

        return new AddressDTO(addressResponseDTO);
    }

    private void validateResponse(HttpResponse<String> response) {
        if (response.statusCode() == 400 || response.statusCode() == 404) {
            throw new BadRequestException("The zipCode format is not correct or the zipCode was not found.");
        }

        if (response.statusCode() == 401 || response.statusCode() == 429) {
            throw new InternalServerErrorException("Error while trying to find the zipCode.");
        }
    }

}

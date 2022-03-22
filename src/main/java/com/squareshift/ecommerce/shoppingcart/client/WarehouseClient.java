package com.squareshift.ecommerce.shoppingcart.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareshift.ecommerce.shoppingcart.config.WarehouseServiceRestProperties;
import com.squareshift.ecommerce.shoppingcart.model.dto.PostalDistanceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class WarehouseClient {

    private final WarehouseServiceRestProperties warehouseServiceRestProperties;

    @Qualifier("WarehouseServiceRestTemplate")
    private final RestTemplate warehouseServiceRestTemplate;

    public Double getPostalDistance(Long postalCode) {
//        return 2.0;
        ResponseEntity<String> response = warehouseServiceRestTemplate.getForEntity(constructURL(postalCode), String.class);
        PostalDistanceResponseDto postalDistanceResponseDto = processResponse(response);
        return postalDistanceResponseDto.getPostalDistance();
    }

    private PostalDistanceResponseDto processResponse(ResponseEntity<String> response) {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory().enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS));
        try {
            return mapper.readValue(response.getBody(), PostalDistanceResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse response", e);
        }
    }

    private String constructURL(Long postalCode) {
        return warehouseServiceRestProperties.getPostalDistanceGetUrl() + "?postal_code=" + postalCode;
    }
}

package com.squareshift.ecommerce.shoppingcart.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareshift.ecommerce.shoppingcart.config.ProductServiceRestProperties;
import com.squareshift.ecommerce.shoppingcart.model.dto.Product;
import com.squareshift.ecommerce.shoppingcart.model.dto.ProductDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductClient {

    private final ProductServiceRestProperties productServiceRestProperties;

    @Qualifier("ProductServiceRestTemplate")
    private final RestTemplate productServiceRestTemplate;


    public ProductDetailResponseDto getProductDetails(Long productId) {
//        return mockResponse();
        ResponseEntity<String> response = productServiceRestTemplate.getForEntity(constructURL(productId), String.class);
        return processResponse(response);
    }

    private ProductDetailResponseDto processResponse(ResponseEntity<String> response) {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory().enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS));
        try {
            return mapper.readValue(response.getBody(), ProductDetailResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse response", e);
        }
    }

    private String constructURL(Long productId) {
        return productServiceRestProperties.getProductDetailsGetUrl() + "/" + productId;
    }

    private ProductDetailResponseDto mockResponse() {
        Product product = Product.builder().productId(1L).name("macbookpro model1").discountPercentage(5F).weightInGrams(300.0).price(200.0).build();
        return ProductDetailResponseDto.builder().product(product).status("SUCCESS").build();
    }

}

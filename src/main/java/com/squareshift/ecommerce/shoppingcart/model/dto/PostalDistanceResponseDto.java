package com.squareshift.ecommerce.shoppingcart.model.dto;

import lombok.Data;

@Data
public class PostalDistanceResponseDto {
    private String status;
    private Double postalDistance;
}

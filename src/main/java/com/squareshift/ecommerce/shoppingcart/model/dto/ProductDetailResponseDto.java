package com.squareshift.ecommerce.shoppingcart.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailResponseDto {
    private String status;
    private String message;
    private Product product;
}

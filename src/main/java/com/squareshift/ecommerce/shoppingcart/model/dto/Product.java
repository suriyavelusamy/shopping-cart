package com.squareshift.ecommerce.shoppingcart.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long productId;
    private String name;
    private Double price;
    private Float discountPercentage;
    private Double weightInGrams;
}
